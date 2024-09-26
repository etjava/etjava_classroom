/*
* 前端导航菜单中点击Framework菜单时 打开新的页面或给出维护中提示
* 2024-09-23
* */

/*打开新的页面*/
function openFrameworkPage(id){
    if(id==null){
        developing();// 维护中弹窗提示  这里正常情况用不到
        return;
    }
    // 请求走后台 然后打开新页面
    /*
        后台请求对应目录下的页面 例如 /framework/mybatis/index.html
    * window.location.href="/framework/"+url;
    * */

    /*
    * 后台走同一个页面 只是加载的数据不同而已
    * 数据根据ID查询到的 菜单信息
    * */
    window.location.href="/framework/"+id;
    //window.location.href="/framework/"+url;
}

// 屏幕中央弹出窗口提示
function developing(){
    layer.open({
        type: 1,
        title: false, // 不显示标题栏
        //closeBtn: false, // 不显示关闭按钮
        shade: 0.8, // 遮罩层
        area: ['300px', '200px'], // 宽高
        content: '<div style=" display: flex;align-items: center;justify-content: center;height: 100px;">' +
            '<img src="/images/logo2.png">' +
            '</div>' +
            '<div style=" display: flex;align-items: center;justify-content: center;height: 20px; font-size: 20px;color: red">功能正在维护中...</div>' // 内容
    });
}