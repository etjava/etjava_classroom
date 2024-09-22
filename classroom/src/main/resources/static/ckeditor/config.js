/**
 * @license Copyright (c) 2003-2023, CKSource Holding sp. z o.o. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
    config.image_previewText=' ';
    config.height = 320;
    // config.filebrowserUploadUrl="/admin/course/ckeditorUpload";
    config.filebrowserUploadUrl="/admin/course/uploadImage2";
    // 不检查版本更新 默认会自动提示更新版本
    config.versionCheck = false;


    /*
    * 配置code
    * https://www.leftso.com/article/38.html
    *
    * 调整图片上传问题
    * https://blog.csdn.net/li_haijiang/article/details/95306016
    * 户籍中考
    * http://www.sichengtech.com/post/2240ed21050d.html#%E5%A4%96%E5%9C%B0%E6%88%B7%E5%8F%A3%E5%9C%A8%E6%83%A0%E5%B7%9E%E5%8F%82%E5%8A%A0%E4%B8%AD%E8%80%83%E6%9C%89%E4%BB%80%E4%B9%88%E6%9D%A1%E4%BB%B6
    * 外地户口在惠州参加中考有什么条件
    * http://www.sichengtech.com/post/2240ed21050d.html#%E5%A4%96%E5%9C%B0%E6%88%B7%E5%8F%A3%E5%9C%A8%E6%83%A0%E5%B7%9E%E5%8F%82%E5%8A%A0%E4%B8%AD%E8%80%83%E6%9C%89%E4%BB%80%E4%B9%88%E6%9D%A1%E4%BB%B6
    * */

    // config.extraPlugins = 'codesnippet,lineutils,widget,dialog';
    config.extraPlugins = 'codesnippet';
    //使用zenburn的代码高亮风格样式 PS:zenburn效果就是黑色背景
    //如果不设置着默认风格为default
    codeSnippet_theme: 'zenburn';
};
