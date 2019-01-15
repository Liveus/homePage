// 定义一个名为 button-counter 的新组件
Vue.component('button-counter', {
    data: function () {
        return {
            count: 0
        }
    },
    template: '<button v-on:click="count++">You clicked me {{ count }} times.</button>'
})

new Vue({ el: '#components-demo' })

/*
var vm = new Vue({
    el:"#form4",
    data:{
        age:0,
        msg:""
    }
})
*/

/*
var vm = new Vue({
    el:"#form3",
    data:{
        checkedNames:[]
    }
})
*/

/*var vm = new Vue({
    el:"#form2",
    data:{
        checked:true
    }
})*/

/*var vm = new Vue({
    el:"#form1",
    data:{
        message : ""
    },
    methods:{
    }
})*/

/*var vm = new Vue({
    el:"#example-5",
    data:{

    },
    methods:{
        submit : function () {
            console.log("submit");
        },
        clear:function () {
            console.log("clear");
        },
        doSomething:function () {
            console.log("doSomething");
        },
        onClick:function () {
            console.log("onClick");
        },
        onCtrlClick:function () {
            console.log("onCtrlClick");
        },
        onClick1:function () {
            console.log("onClick1")
        }
    }
})*/

/*var vm = new Vue({
    el: '#example-4',
    methods: {
        warn: function (message, event) {
            // 现在我们可以访问原生事件对象
            if (event) event.preventDefault()
            alert(message)
        }
    }
})*/


/*new Vue({
    el: '#example-3',
    methods: {
        say: function (message) {
            alert(message)
        }
    }
})*/

/*var example2 = new Vue({
    el: '#example-2',
    data: {
        name: 'Vue.js'
    },
    // 在 `methods` 对象中定义方法
    methods: {
        greet: function (event) {
            // `this` 在方法里指向当前 Vue 实例
            alert('Hello ' + this.name + '!')
            // `event` 是原生 DOM 事件
            if (event) {
                alert(event.target.tagName)
            }
        }
    }
})

// 也可以用 JavaScript 直接调用方法
example2.greet() // => 'Hello Vue.js!'*/

/*var example1 = new Vue({
    el: '#example-1',
    data: {
        counter: 0
    }
})*/

/*
var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!'
    }
})

var app2 = new Vue({
    el: '#app-2',
    data: {
        message: '页面加载于 ' + new Date().toLocaleString()
    }
})

var app3 = new Vue({
    el: '#app-3',
    data: {
        seen: true
    }
})

var app4 = new Vue({
    el: '#app-4',
    data: {
        todos: [
            { text: '学习 JavaScript' },
            { text: '学习 Vue' },
            { text: '整个牛项目' }
        ]
    }
})

var app5 = new Vue({
    el: '#app-5',
    data: {
        message: 'Hello Vue.js!'
    },
    methods: {
        reverseMessage: function () {
            this.message = this.message.split('').reverse().join('')
        }
    }
})

var app6 = new Vue({
    el: '#app-6',
    data: {
        message: 'Hello Vue!'
    }
})

Vue.component('todo-item', {
    props: ['todo'],
    template: '<li>{{ todo.text }}</li>'
})

var app7 = new Vue({
    el: '#app-7',
    data: {
        groceryList: [
            { id: 0, text: '蔬菜' },
            { id: 1, text: '奶酪' },
            { id: 2, text: '随便其它什么人吃的东西' }
        ]
    }
})*/

/*var vm = new Vue({
    // 选项
})

var obj = {
    foo: 'bar'
}

//Object.freeze(obj)

new Vue({
    el: '#app',
    data: obj
})*/

/*
var data = { a: 1 }
var vm = new Vue({
    el: '#example',
    data: data,
    created: function () {
        // `this` 指向 vm 实例
        console.log('a is: ' + this.a)
    }
})

vm.$data === data // => true
vm.$el === document.getElementById('example') // => true

// $watch 是一个实例方法
vm.$watch('a', function (newValue, oldValue) {
    // 这个回调将在 `vm.a` 改变后调用
    console.log("11");
})*/

/*var data = { rawHtml: "<span style='color: red'>red</span>" }
var vm = new Vue({
    el: '#test',
    data: data,
    created: function () {
        // `this` 指向 vm 实例
        console.log('a is: ' + this.rawHtml)
    }
})*/

/*
var app3 = new Vue({
    el: '#buttom',
    data: {
        isButtonDisabled: false
    }
})*/

/*
var data = {
    number: 1,
    ok:true,
    message:"asd sad"}
var vm = new Vue({
    el: '#jsExpress',
    data: data
})*/

/*var vm = new Vue({
    el: '#example',
    data: {
        message: 'Hello'
    },
    computed: {
        // 计算属性的 getter
        reversedMessage: function () {
            // `this` 指向 vm 实例
            return this.message.split('').reverse().join('')
        }
    },
    methods: {
        reversedMessage1: function () {
            return this.message.split('').reverse().join('')
        }
    }
})*/

/*
var vm = new Vue({
    el: '#demo',
    data: {
        firstName: 'Foo',
        lastName: 'Bar',
        fullName: 'Foo Bar'
    },
    watch: {
        firstName: function (val) {
            this.fullName = val + ' ' + this.lastName
        },
        lastName: function (val) {
            this.fullName = this.firstName + ' ' + val
        }
    }
})*/

/*
var vm = new Vue({
    el :"#bind",
    data:{
        isActive :true
    }
})*/

/*
var vm = new Vue({
    el:"#style",
    data:{
        styleObject:{
            activeColor :'red',
            fontSize:30
        }
    }
})*/

/*var vm = new Vue({
    el:"#style",
    data:{
        baseStyles :'red',
        overridingStyles:30
        }
})*/

/*var vm = new Vue({
    el:"#if",
    data:{
        ok:true
    }
})*/

/*
var vm = new Vue({
    el:"#if2",
    data:{
        ok:true
    }
})*/

/*
var example1 = new Vue({
    el: '#for',
    data: {
        items: [
            { message: 'Foo' },
            { message: 'Bar' }
        ]
    }
})*/

/*
var example2 = new Vue({
    el: '#example-2',
    data: {
        parentMessage: 'Parent',
        items: [
            { message: 'Foo' },
            { message: 'Bar' }
        ]
    }
})*/

/*
new Vue({
    el: '#v-for-object',
    data: {
        object: {
            firstName: 'John',
            lastName: 'Doe',
            age: 30
        }
    }
})*/