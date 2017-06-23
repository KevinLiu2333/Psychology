package com.klsw.website.util;

import org.patchca.color.ColorFactory;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.Captcha;
import org.patchca.service.CaptchaService;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.word.RandomWordFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 图片验证码工具类
 * @author wangys
 *
 */
public class CaptchaServiceFactory {

	private static final int DEFAULT_FONT_SIZE = 30;
	private static final int DEFAULT_WORD_LENGTH = 4;
	private static int DEFAULT_WIDTH = 80;
	private static int DEFAULT_HEIGHT = 35;
	
	private static ExConfigurableCapchaService ecs = new ExConfigurableCapchaService();
	
	/**
	 * 
	 * @param fontSize 字体大小，如果设置为0则使用默认字体大小
	 * @param length 单词长度，如果设置为0则默认长度为4
	 * @param width 图片宽度，如果设置为0则使用默认宽度为80个像素
	 * @param height 图片高度，如果设置为0则使用默认高度为35个像素
	 * @return
	 */
	public static CaptchaService create(int fontSize, int length, int width, int height) {
		if (fontSize == 0) {
			fontSize = DEFAULT_FONT_SIZE;
		}
		if (length == 0) {
			length = DEFAULT_WORD_LENGTH;
		}
		if (width == 0) {
			width = DEFAULT_WIDTH;
		}
		if (height == 0) {
			height = DEFAULT_HEIGHT;
		}
		//设置字体大小
		RandomFontFactory font = new RandomFontFactory();
		font.setMinSize(fontSize);
		font.setMaxSize(fontSize + 10);
		//设置单词长度
		RandomWordFactory word = new RandomWordFactory();
		word.setMinLength(length);
		word.setMaxLength(length + 2);
		//干扰线波纹
		CurvesRippleFilterFactory ripp = new CurvesRippleFilterFactory();
		ripp.setStrokeMax(100);
		//图片大小
		ecs.setWidth(width);
		ecs.setHeight(height);
		
		ecs.setFontFactory(font);
		ecs.setWordFactory(word);
		ecs.setFilterFactory(ripp);
		return ecs;
	}
	
	private static class ExConfigurableCapchaService extends ConfigurableCaptchaService {
		
		private static final Random RANDOM = new Random();
		
		private List<SingleColorFactory> colorList = new ArrayList<>();
		
		private ExConfigurableCapchaService() {
			colorList.add(new SingleColorFactory(Color.BLUE));
			colorList.add(new SingleColorFactory(Color.BLACK));
			colorList.add(new SingleColorFactory(Color.RED));
			colorList.add(new SingleColorFactory(Color.PINK));
			colorList.add(new SingleColorFactory(Color.ORANGE));
			colorList.add(new SingleColorFactory(Color.GREEN));
			colorList.add(new SingleColorFactory(Color.MAGENTA));
			colorList.add(new SingleColorFactory(Color.CYAN));
		}
		
		public ColorFactory getColorFactory() {
			int index = RANDOM.nextInt(colorList.size());
			return colorList.get(index);
		}
		
		/* (non-Javadoc)
		 * @see org.patchca.service.AbstractCaptchaService#getCaptcha()
		 */
		@Override
		public Captcha getCaptcha() {
			ColorFactory cf = getColorFactory();
			CurvesRippleFilterFactory ripp = (CurvesRippleFilterFactory)this.getFilterFactory();
			ripp.setColorFactory(cf);
			this.setColorFactory(cf);
			return super.getCaptcha();
		}
	}
}



