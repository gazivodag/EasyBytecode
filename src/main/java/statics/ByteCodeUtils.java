package statics;

import api.ObjectFromConstantPool;
import api.ObjectType;
import api.objects.CodeArg;
import api.objects.CodeLine;
import api.objects.typeconstants.DoubleConstant;
import api.objects.typeconstants.FloatConstant;
import api.objects.typeconstants.ImLazyConstant;
import api.objects.typeconstants.IntegerConstant;
import api.objects.typeconstants.LongConstant;
import api.objects.typeconstants.StringConstant;
import api.objects.typeconstants.Utf8Constant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;

public class ByteCodeUtils
{

	/**
	 * Converts a decimal pointer into two hex args used by the JVM
	 *
	 * @return
	 */
	public static int hexToDecimal(int arg1, int arg2)
	{
		String[] argz = new String[2];
		argz[0] = Integer.toHexString(arg1);
		argz[1] = Integer.toHexString(arg2);
		for (int i = 0; i < argz.length; i++)
		{
			String arg = argz[i];
			if (arg.length() < 2)
			{
				argz[i] = "0" + arg;
			}
		}
		int pointer = Integer.parseInt(argz[0] + argz[1], 16);
		return pointer;
	}

	/**
	 * Converts a decimal to a 4-digit hex value
	 * @param decimal
	 * @return
	 */
	public static String decimalToHex(int decimal)
	{
		String hexString = Integer.toHexString(decimal);
		if (hexString.length() == 3)
		{
			hexString = "0" + hexString;
		}
		return hexString;
	}

	public static CodeLine[] getCodeFromConstructor(CtConstructor ctConstructor)
	{
		return getCodeFromMethod(ctConstructor.getMethodInfo());
	}

	public static CodeLine[] getCodeFromMethod(CtMethod ctMethod)
	{
		return getCodeFromMethod(ctMethod.getMethodInfo());
	}

	private static CodeLine[] getCodeFromMethod(MethodInfo methodInfo)
	{
		List<CodeLine> codeLineList = new ArrayList<>();
		ConstPool constPool = methodInfo.getConstPool();
		ObjectFromConstantPool[] dumpedPool = getAllConstants(constPool);

		try
		{
			CodeIterator codeIterator = methodInfo.getCodeAttribute().iterator();
			int pos = 0;
			while (codeIterator.hasNext())
			{
				pos = codeIterator.next();
				int opcode = codeIterator.byteAt(pos);

				if ((pos + 2) >= codeIterator.getCodeLength())
				{
					continue;
				}

				int arg1 = codeIterator.byteAt(pos + 1);
				int arg2 = codeIterator.byteAt(pos + 2);

				CodeArg codeArg = new CodeArg(arg1, arg2);

				if (codeArg.getAsDecimal() > dumpedPool.length)
				{
					continue;
				}

				ObjectFromConstantPool objectFromConstantPool = dumpedPool[codeArg.getAsDecimal() - 1];
				CodeLine codeLine = new CodeLine(pos, opcode, codeArg, objectFromConstantPool);
				codeLineList.add(codeLine);
			}

			CodeLine[] codeLines = new CodeLine[codeLineList.size()];
			return codeLineList.toArray(codeLines);
		}
		catch (BadBytecode badBytecode)
		{
			badBytecode.printStackTrace();
		}
		return null;
	}

	public static ObjectFromConstantPool[] getAllConstants(ConstPool constPool)
	{
		try
		{
			List<ObjectFromConstantPool> constantList = new LinkedList<>();

			for (int i = 1; i < constPool.getSize(); i++)
			{
				ObjectType objectType = ObjectType.findByConst(constPool.getTag(i));

				if (objectType == null)
				{
					continue;
				}

				switch (objectType)
				{
					case CONST_UTF8:
						Utf8Constant utf8Constant = new Utf8Constant(constPool.getUtf8Info(i));
						constantList.add(utf8Constant);
						break;
					case CONST_INTEGER:
						IntegerConstant integerConstant = new IntegerConstant(constPool.getIntegerInfo(i));
						constantList.add(integerConstant);
						break;
					case CONST_FLOAT:
						FloatConstant floatConstant = new FloatConstant(constPool.getFloatInfo(i));
						constantList.add(floatConstant);
						break;
					case CONST_LONG:
						LongConstant longConstant = new LongConstant(constPool.getLongInfo(i));
						constantList.add(longConstant);
						break;
					case CONST_DOUBLE:
						DoubleConstant doubleConstant = new DoubleConstant(constPool.getDoubleInfo(i));
						constantList.add(doubleConstant);
						break;
					case CONST_STRING:
						StringConstant stringConstant = new StringConstant(constPool.getStringInfo(i));
						constantList.add(stringConstant);
						break;
					default:
						ImLazyConstant imLazyConstant = new ImLazyConstant(objectType);
						constantList.add(imLazyConstant);
						break;
				}
			}

			ObjectFromConstantPool[] constantArr = new ObjectFromConstantPool[constantList.size()];
			return constantList.toArray(constantArr);

		}
		catch (Throwable throwable)
		{
			throwable.printStackTrace();
		}
		return null;
	}

	public static void replaceCodeLine(MethodInfo methodInfo, CodeLine codeLine)
	{
		CodeIterator codeIterator = methodInfo.getCodeAttribute().iterator();

		final int pos = codeLine.getPos();
//		System.out.println(pos + ": Writing " + codeLine.getOpcode() + " to " + (pos) + " (was " + codeIterator.byteAt((pos)) + ")");
//		System.out.println(pos + ": Writing " + codeLine.getCodeArg().getArg1() + " to " + (pos + 1) + " (was " + codeIterator.byteAt((pos + 1)) + ")");
//		System.out.println(pos + ": Writing " + codeLine.getCodeArg().getArg2() + " to " + (pos + 2) + " (was " + codeIterator.byteAt((pos + 2)) + ")");

		codeIterator.writeByte(codeLine.getOpcode(), pos);
		codeIterator.writeByte(codeLine.getCodeArg().getArg1(), (pos + 1));
		codeIterator.writeByte(codeLine.getCodeArg().getArg2(), (pos + 2));
	}

	/**
	 * Arrays are unorganized. You must get a CodeLine from it's position in the method.
	 * @param codeLines
	 * @param pos
	 * @return
	 */
	public static CodeLine getCodeLineFromPos(CodeLine[] codeLines, int pos)
	{
		for (CodeLine line : codeLines)
		{
			if (line.getPos() == pos)
			{
				return line;
			}
		}
		return null;
	}

}
