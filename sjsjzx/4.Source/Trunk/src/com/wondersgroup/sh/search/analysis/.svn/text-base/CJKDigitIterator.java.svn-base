package com.wondersgroup.sh.search.analysis;

public final class CJKDigitIterator {
  /** Indicates the end of iteration */
  public static final int DONE = -1;
  
  char text[];
  int length;
  int current;
  int end;
  boolean first;
  int digitLength;
  boolean isLetter;
  
  CJKDigitIterator(int digitLength) {
  	this.digitLength = digitLength;
  }
  	
  /* 012 */
  int next() {
  	if( !first ) {
  		if( isLetter ) { // must be digit
  			current = end;
  			end = next(current);
  			isLetter = false;
  		}
  		else {
  			current += 1;
  			end += 1;
  	  	if( end > length ) {
  	  		return end = DONE;
  	  	}
  	  	
  			if( Character.isLetter(text[end-1]) ) {
  				isLetter = true;
  				current = end - 1;
  				end = this.nextDigit(current);
  			}
  		}
  	}
  	else {
  		first = false;
  		if( Character.isLetter(text[current]) ) {
  			end = nextDigit(current);
  			isLetter = true;
  		}
  		else {
	  		end = next(current);
	  		isLetter = false;
  		}
  	}
  	
  	if( end > length ) {
  		return end = DONE;
  	}
  	
    return end;
  }

  void setText(char text[], int length) {
    this.text = text;
    this.length = length;
    current = 0;
    end = 0;
    first = true;
    isLetter = false;
  }
  
  int nextDigit(int from) {
  	for(int i = from; i < length; i++) {
  		if(Character.isDigit(text[i]))
  			return i;
  	}
  	return length;
  }
  
  int next(int from) {
  	int index = from;
  	int count = 1;
  	while((index < length) && Character.isDigit(text[index]) && (count <= this.digitLength)) {
  		count++;
  		index++;
  	}
  	return index;
  }
}
