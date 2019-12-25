package api.objects;

import api.ObjectFromConstantPool;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * A Sophisticated CodeIterator object
 */
@Data
@AllArgsConstructor
public class CodeLine
{
	private int pos;
	private int opcode;
	private CodeArg codeArg;
	private ObjectFromConstantPool objectFromConstantPool;
}
