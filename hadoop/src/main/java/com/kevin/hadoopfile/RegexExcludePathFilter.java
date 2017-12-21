package com.kevin.hadoopfile;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

/**
 * 路径过滤器
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/8/23
 * Time: 13:20
 */
public class RegexExcludePathFilter implements PathFilter {

    private final String regex;

    public RegexExcludePathFilter(String regex) {
        this.regex = regex;
    }

    public boolean accept(Path path) {
        return !path.toString().matches(regex);
    }
}
