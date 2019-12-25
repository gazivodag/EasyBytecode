package api.objects.typeconstants;

import api.ObjectFromConstantPool;
import api.ObjectType;
import lombok.Getter;
import lombok.Setter;

public class Utf8Constant extends ObjectFromConstantPool
{
	@Getter
	@Setter
	private String string;

	public Utf8Constant(String string)
	{
		super(ObjectType.CONST_UTF8);
		this.string = string;
	}
}
