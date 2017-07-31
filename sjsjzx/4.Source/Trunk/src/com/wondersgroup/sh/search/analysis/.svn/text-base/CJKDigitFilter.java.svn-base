package com.wondersgroup.sh.search.analysis;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.apache.lucene.util.ArrayUtil;
import org.apache.lucene.util.RamUsageEstimator;

public class CJKDigitFilter extends TokenFilter {
  private final CharTermAttribute termAttribute = addAttribute(CharTermAttribute.class);
  private final OffsetAttribute offsetAttribute = addAttribute(OffsetAttribute.class);
  private final TypeAttribute typeAttribute = addAttribute(TypeAttribute.class);
  
  private char savedBuffer[] = new char[1024];
  private int savedStartOffset;
  private String savedType;
  private boolean hasSavedState = false;
  private CJKDigitIterator iterator;
  private int digitLength;
  
  public CJKDigitFilter(TokenStream in) {
  	this(in, 2);
  }
  
  public CJKDigitFilter(TokenStream in, int digitLength) {
  	super(in);
  	this.digitLength = digitLength;
  	this.iterator = new CJKDigitIterator(digitLength);
  }
  
	@Override
	public boolean incrementToken() throws IOException {
    while (true) {
      if (!hasSavedState) {
        // process a new input word
        if (!input.incrementToken()) {
          return false;
        }

        int termLength = termAttribute.length();
        char[] termBuffer = termAttribute.buffer();
        if ("double".equalsIgnoreCase(typeAttribute.type()) || !hasEnoughDigit(termBuffer, termLength)) {
        	return true;
        }
        
        iterator.setText(termBuffer, termLength);
        iterator.next();

        // word of no delimiters, or protected word: just return it
        if ((iterator.current == 0 && iterator.end == termLength)) {
          return true;
        }
        
        saveState();
      }
      
      if( iterator.end == CJKDigitIterator.DONE ) {
        hasSavedState = false;
        continue;
      }
      
      generatePart();
      iterator.next();
      return true;
    }
	}

	private boolean hasEnoughDigit(char[] termBuffer, int termLength) {
		int count = 0;
		for(int i = 0; i < termLength; i++) {
			count = Character.isDigit(termBuffer[i]) ? count + 1 : 0; 
			if( count >= digitLength ) {
				return true;
			}
		}
		return false;
	}
	
  private void saveState() {
    savedStartOffset = offsetAttribute.startOffset();
    savedType = typeAttribute.type();

    if (savedBuffer.length < termAttribute.length()) {
      savedBuffer = new char[ArrayUtil.oversize(termAttribute.length(), RamUsageEstimator.NUM_BYTES_CHAR)];
    }

    System.arraycopy(termAttribute.buffer(), 0, savedBuffer, 0, termAttribute.length());
    hasSavedState = true;
  }
  
	private void generatePart() {
    clearAttributes();
    termAttribute.copyBuffer(savedBuffer, iterator.current, iterator.end - iterator.current);

    int startOffSet = savedStartOffset + iterator.current;
    int endOffSet = savedStartOffset + iterator.end;

    offsetAttribute.setOffset(startOffSet, endOffSet);
    typeAttribute.setType(savedType);
	}
}
