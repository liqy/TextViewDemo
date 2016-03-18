# TextViewDemo
##安卓实现关键字加红，点击字体打电话，textview斜体，电商删除线，绿色下划线，图片（表情，图文混排）


#先看实例图
![image](https://github.com/qiushi123/TextViewDemo/blob/master/images/qcl_meitu_3.jpg?raw=true)

#一，后台控制搜索关键字加红
###1，后台写好对应html，后台负责显示什么颜色		
        txtInfo.setText(
                Html.fromHtml("一个<font " + "color=\"red\">手机</font><em>手机</em>号码是一直在流转的" +
                                "created in the Java source code using HTML."));
        txtInfo.setMovementMethod(LinkMovementMethod.getInstance());

        txtInfo2.setText(
                Html.fromHtml(
                        "<b>加粗:</b> 点击这个超链接调到网站 " +
                                "<a href=\"http://www.baidu.com\">link</a> "));
        txtInfo2.setMovementMethod(LinkMovementMethod.getInstance());

#二，通过spannableStringBuilder实现关键字加红，点击字体打电话，斜体，删除线，绿色下划线，图片（表情，图文混排）

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
		
#我的个人博客
## http://blog.csdn.net/qiushi_1990
		
