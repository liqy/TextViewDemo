package com.textviewdemo;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView txtInfo, txtInfo2, txtInfo3, txtInfo4, txtInfo5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtInfo = (TextView) findViewById(R.id.txtInfo);
        txtInfo2 = (TextView) findViewById(R.id.txtInfo2);
        txtInfo3 = (TextView) findViewById(R.id.txtInfo3);
        txtInfo4 = (TextView) findViewById(R.id.txtInfo4);
        txtInfo5 = (TextView) findViewById(R.id.txtInfo5);

        //红色打电话斜体删除线绿色下划线图片
        initTextView();

        //可以用来做表情
        initTextView2();

        //超链接可以跳转
        initTvURL();

        //换行两种颜色
        SpannableStringBuilder highlight = highlight("带有\\n换行符的字符串\n都可以用此方法显示2种颜色", Color.parseColor("#2bc47e"), Color.parseColor
                ("#00ff7e"), dip2px(15));
        txtInfo5.setText(highlight);
    }


    private void initTvURL() {

        txtInfo.setText(
                Html.fromHtml("一个<font " + "color=\"red\">手机</font><em>手机</em>号码是一直在流转的" +
                        "created in the Java source code using HTML."));

        txtInfo.setMovementMethod(LinkMovementMethod.getInstance());

        txtInfo2.setText(
                Html.fromHtml(
                        "<b>加粗:</b> 点击这个超链接调到网站 " +
                                "<a href=\"http://www.baidu.com\">link</a> "));
        txtInfo2.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initTextView() {
        //        spannableStringBuilder 用法详解：
        /*可以实现以下效果
        * 1，点击字体打电话
        * 2，红色字体，搜索关键字变红
        * 3，斜体
        * 4，删除线
        * 5，下划线
        * 6，图文混排
        * */
        SpannableString ss = new SpannableString("红色打电话斜体删除线绿色下划线图片:.");
        //用颜色标记文本
        ss.setSpan(new ForegroundColorSpan(Color.RED), 0, 2,
                //setSpan时需要指定的 flag,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE(前后都不包括).
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //用超链接标记文本
        ss.setSpan(new URLSpan("tel:4155551212"), 2, 5,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //用样式标记文本（斜体）
        ss.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 5, 7,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //用删除线标记文本
        ss.setSpan(new StrikethroughSpan(), 7, 10,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //用下划线标记文本
        ss.setSpan(new UnderlineSpan(), 10, 16,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //用颜色标记
        ss.setSpan(new ForegroundColorSpan(Color.GREEN), 10, 13,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //获取Drawable资源
        Drawable d = getResources().getDrawable(R.drawable.contact_image);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());

        //创建ImageSpan
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);

        //用ImageSpan替换文本
        ss.setSpan(span, 18, 19, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);


        txtInfo3.setText(ss);
        txtInfo3.setMovementMethod(LinkMovementMethod.getInstance()); //实现文本的滚动
    }

    private void initTextView2() {
         /*  通常用于显示文字，但有时候也需要在文字中夹杂一些图片，比如QQ中就可以使用表情图片，又比如需要的文字高亮显示等等，
        如何在android中也做到这样呢？
        记得android中有个android.text包，这里提供了对文本的强大的处理功能。
        添加图片主要用SpannableString和ImageSpan类：*/

        //要显示文字
        String str = "我是用图片替代文字，做表情用的,表情[smile]";

        //获取要加载替换的图片资源
        Drawable drawable = getResources().getDrawable(R.drawable.smil2);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        //获取Drawable资源
        Drawable d = getResources().getDrawable(R.drawable.contact_image);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());

        //需要处理的文本，[smile]是需要被替代的文本
        SpannableString spannable = new SpannableString(str + "[smile]");

        //要让图片替代指定的文字就要用ImageSpan
        ImageSpan span1 = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        ImageSpan span2 = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);

        //开始替换，注意第2和第3个参数表示从哪里开始替换到哪里替换结束（start和end）
        //最后一个参数类似数学中的集合,[5,12)表示从5到12，包括5但不包括12
        int length = "[smile]".length();
        spannable.setSpan(span1, str.length() - length, str.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spannable.setSpan(span2, str.length(), str.length() + length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        txtInfo4.setText(spannable);
    }

        /*

            //        将需要的文字高亮显示：
            public void highlight(int start, int end) {
                SpannableStringBuilder spannable = new SpannableStringBuilder("用于可变字符串 ");//用于可变字符串
                ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
                spannable.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                setText(spannable);
            }

            //        加下划线：
            public void underline(int start, int end) {
                SpannableStringBuilder spannable = new SpannableStringBuilder(getText().toString());
                CharacterStyle span = new UnderlineSpan();
                spannable.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                setText(spannable);
            }

            //        组合运用：
            public void highlightAndunderline(int start, int end) {
                SpannableStringBuilder spannable = new SpannableStringBuilder(getText().toString());
                CharacterStyle span_1 = new StyleSpan(android.graphics.Typeface.ITALIC);
                CharacterStyle span_2 = new ForegroundColorSpan(Color.RED);
                spannable.setSpan(span_1, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannable.setSpan(span_2, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                setText(spannable);
            }
        */


    //案例：带有\n换行符的字符串都可以用此方法显示2种颜色

    /**
     * 带有\n换行符的字符串都可以用此方法显示2种颜色
     *
     * @param text
     * @param color1
     * @param color2
     * @return
     */
    public SpannableStringBuilder highlight(String text, int color1, int color2, int fontSize) {
        SpannableStringBuilder spannable = new SpannableStringBuilder(text);//用于可变字符串
        CharacterStyle span_0 = null, span_1 = null, span_2;
        int end = text.indexOf("\n");
        if (end == -1) {//如果没有换行符就使用第一种颜色显示
            span_0 = new ForegroundColorSpan(color1);
            spannable.setSpan(span_0, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            span_0 = new ForegroundColorSpan(color1);
            span_1 = new ForegroundColorSpan(color2);
            spannable.setSpan(span_0, 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(span_1, end + 1, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            span_2 = new AbsoluteSizeSpan(fontSize);//字体大小
            spannable.setSpan(span_2, end + 1, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }


    //dp转px
    public int dip2px(float dpValue) {
        final float scale = MainActivity.this.getResources().getDisplayMetrics().density;
        return (int) ((dpValue * scale) + 0.5f);
    }

}
