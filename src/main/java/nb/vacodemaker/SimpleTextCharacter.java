package nb.vacodemaker;

import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import org.patchca.text.renderer.TextCharacter;

public class SimpleTextCharacter extends TextCharacter
{
	public AttributedCharacterIterator iterator() {
		AttributedString aString = new AttributedString(String
				.valueOf(getCharacter()));
		aString.addAttribute(TextAttribute.FONT, getFont(), 0, 1);
		aString.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_EXTRABOLD, 0, 1);
		return aString.getIterator();
	}
}
