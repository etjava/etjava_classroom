const defaultFunction = () => { }

/**
 * 检查当前文档是否支持复制、剪切命令
 * (() => {})() 是一个立即执行的匿名函数，目的是为了在函数执行时立即返回值，而不用写 return 语句
 */
const IS_SUPPORTED = (() => {
    const actions = ['copy', 'cut']
    let isSupport = !!document.queryCommandSupported // 用于检查当前文档是否支持特定命令的DOM方法，返回true/false

    actions.forEach((action) => {
        isSupport = isSupport && !!document.queryCommandSupported(action)
    })
    return isSupport
})()

/**
 * 定义并导出一个Clipboard类，用于复制、剪切文本到系统剪切板
 * 一个模块只能有一个默认导出，因此这里导出的是一个类，而不是一个函数
 */
class Clipboard {
    // 构造函数 - 创建类的实例时调用，进行初始化操作，接受两个参数value和options
    constructor(val, options) {
        this.resolveOptions(options)
        this.copy(val)
    }
    // 解析和处理传入的选项 - options为选项对象，默认{}
    resolveOptions(options = {}) {
        this.fail = (typeof options.fail === 'function') ? options.fail : defaultFunction // 设置错误方法
        this.success = (typeof options.success === 'function') ? options.success : defaultFunction // 设置成功方法
    }
    // 复制前的准备工作 - 当前文档不支持复制命令时，直接调用失败方法，否则进行复制操作
    copy(val) {
        this.val = val
        if (IS_SUPPORTED) {
            this.createInput(val, data => data ? this.success() : this.fail()) // 当前文档支持复制命令，进行复制操作
        } else {
            this.fail() // 当前文档不支持复制命令，则调用失败方法
        }
    }
    // 复制文本到系统剪切板 - val为要复制的文本，fuc为成功/失败回调函数
    createInput(val, func) {
        try {
            const oInput = document.createElement('input') // 创建一个input元素
            oInput.value = val // 设置input元素的值为要复制的文本
            oInput.style.opacity = '0' // 设置input元素的透明度为0，防止用户看到光标
            oInput.style.position = 'absolute' // 设置input元素的位置为绝对定位，也是为了隐藏光标
            document.body.appendChild(oInput) // 将input元素添加到body中
            oInput.setSelectionRange(0, -1) // 选择input元素的全部内容
            oInput.select() // 选中input元素的内容
            const status = document.execCommand('Copy') // 执行复制命令
            oInput.style.display = 'none' // 隐藏input元素
            oInput.remove() // 删除input元素
            func && func(status) // 执行回调函数，传入复制命令的执行结果 status为true/false
        } catch (e) {
            func && func(false) // 执行回调函数，传入false表示复制失败
        }
    }
}
