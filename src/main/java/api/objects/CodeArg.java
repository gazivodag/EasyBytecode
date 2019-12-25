package api.objects;

import lombok.Data;
import statics.ByteCodeUtils;

/**
 * This class will carry integer information and give the integer back as decimal, hex, or split the pointer into two args
 */
@Data
public class CodeArg
{
	private final int arg1;
	private final int arg2;

	public CodeArg(int arg1, int arg2)
	{
		this.arg1 = arg1;
		this.arg2 = arg2;
	}

	public CodeArg(int decimal)
	{
		String hex = Integer.toHexString(decimal);
		if (hex.length() == 3)
		{
			hex = "0" + hex;
		}

		int ye1 = Integer.parseInt(hex.substring(0, 2), 16);
		int ye2= Integer.parseInt(hex.substring(2, 4), 16);

		System.out.println("CODEARG CREATION: arg1: " + ye1 + " arg2: " + ye2);

		this.arg1 = ye1;
		this.arg2 = ye2;
	}

	public int getAsDecimal()
	{
		return ByteCodeUtils.hexToDecimal(arg1, arg2);
	}

	public String getAsHex()
	{
		return ByteCodeUtils.decimalToHex(getAsDecimal());
	}

//	public int getAsDecimalArg1()
//	{
//		return Integer.parseInt(getAsHex().substring(0, 2), 16);
//	}
//
//	public int getAsDecimalArg2()
//	{
//		return Integer.parseInt(getAsHex().substring(2, 4), 16);
//	}

}