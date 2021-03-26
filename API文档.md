# API文档

## 一. 用户模块

### 1.1 用户登录 

> /user/login
>
> 说明: 调用此接口后,后台将会返回用户的基本信息,并在session中存入用户的基本信息(如id,openid,...),并把 JSESSIONID以cookie的形式发给小程序,以后小程序每次调用后台接口时都需要在请求头里带上JSESSIONID;当session过期则需要从新登陆以获取JSESSIONID

**参数** 

| 属性    | 类型       | 默认值 | 必填 | 说明                                                         |
| ------- | ---------- | ------ | ---- | ------------------------------------------------------------ |
| wxCode  | String     |        | 是   | 在小程序中调用wx.login()得到的code                           |
| rawData | json字符串 |        | 否   | 用户的基本信息(如,性别,昵称,城市,省,市,头像url),若用户第一次登陆,则必须传入rawData;若用户之前已经登录过小程序,则可不用传 |

**成功**

```
msg: "登录成功"
userInfo:
    city: "..."
    country: "China"
    gender: "1"
    id: 4
    nickname: "须尽欢"
    openid: null
    phoneNumber: 18000733007
    province: "..."
    roleId: 1
    sessionKey: null
    userAvatar: "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKGDO1UENuj6uN3Fp53WqxcsVF3CfG8Q64GvJYtBkl9icKA1QNia786JkjFiaUamn3BQLd1INMhgoiaMA/132"

```

### 1.2 获取用户手机号

> /user/getUserPhone
>
> 说明:
>
> ​	如果用户之前从来没有登录过小程序,则在调用此接口时,会被要求先登录.
>
> ​	如果用户之前登录过小程序(即数据库中存有用户的基本信息), 且数据库中也有该用户的手机号,那么不需要传入密文和向量即可直接获取用户的手机号
>
> ​	如果用户之前登录过小程序,但数据库中没有该用户的手机号,则要求传入密文和向量以获取手机号

| 参数          | 类型   | 默认值 | 必填 | 说明                   |
| ------------- | ------ | ------ | ---- | ---------------------- |
| wxCode        | String |        | 是   | 调用wx.login得到的code |
| encryptedData | String |        | 否   | 密文                   |
| iv            | String |        | 否   | 向量                   |

成功:

```
{
	purePhoneNumber: "18000733008"
}
```



## 二. 商品模块

### 2.1 添加商品

> /product/add
>
> Post
>
> 说明: 通过此接口来上传商品相关信息以及该种商品的各种规格和相应的价格
>
> ?代表下标,从0开始; 因为商品规格的数量不定,所以这里采用list的方式存放规格
>
> 新添加参数: concretTypeId

**参数**





| 属性                           | 类型   | 默认值 | 必填 | 说明                                                         |
| ------------------------------ | ------ | ------ | ---- | ------------------------------------------------------------ |
| productName                    | String |        | 是   | 商品名称                                                     |
| overview                       | String |        | 是   | 商品简介                                                     |
| typeId                         | int    |        | 是   | 商品类型(二手商品,普通商品,服务性商品...)一般是通过接口(/type)查出所有的类型 |
| concretTypeId                  | int    |        | 是   | 商品具体类型                                                 |
| surfaceImg                     | String |        | 是   | 商品封面图                                                   |
| slideImg                       | String |        | 是   | 商品轮播图,多个url用逗号分隔                                 |
| introImg                       | String |        | 是   | 商品详情图,多个url用逗号分隔                                 |
| weight                         | String |        | 是   | 商品权重(1,2,3...10),权重越大,则查询出来时越靠前             |
| state                          | String |        | 是   | 商品的状态,1:出售,0:暂不出售/下架                            |
| specification[?].name          | String |        | 是   | 规格名称                                                     |
| specification[?].originalPrice | double |        | 是   | 此规格商品原价                                               |
| specification[?].currentPrice  | double |        | 是   | 此规格商品现价                                               |
| specification[?].inventory     | int    |        | 是   | 此规格商品的库存数                                           |
| specification[?].img           | int    |        | 是   | 此规格商品的图片                                             |
| specification[?].state         | String |        | 否   | 此规格商品的状态(1:货源充足,0已售罄,-1已下架)                |

### 2.2 查询商品

> /product/query
>
> 说明: 

**参数**

| 属性        | 类型   | 默认值 | 必填 | 说明                                 |
| ----------- | ------ | ------ | ---- | ------------------------------------ |
| currentPage | int    | 1      | 否   | 当前页码,默认从1开始                 |
| pageSize    | int    | 10     | 否   | 每页查询数量                         |
| typeId      | int    |        | 否   | 商品类型id,如果不传入,则查询所有类型 |
| state       | String |        | 否   | 商品状态                             |

成功

> 查询list时不返回slideImg和introImg,只有当调用根据id查找商品时会显示slideImg和introImg

```
{
    "totalPage": 4,
    "products": [
        {
            "id": 57,
            "productName": "typeC数据线50",
            "overview": "高质量typeC数据线",
            "typeId": 3,
            "concretTypeId":1,
            "surfaceImg": "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e",
            "slideImg": null,
            "introImg": null,
            "weight": "10",
            "addTime": "2019-11-29 21:10:39",
            "modifyTime": "2019-11-29 21:10:39",
            "state": "1"
        },
        {
            "id": 28,
            "productName": "二手平板笔记本",
            "overview": "二手笔记本贱卖",
            "typeId": 1,
            "surfaceImg": "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e",
            "slideImg": null,
            "introImg": null,
            "weight": "9",
            "addTime": "2019-11-29 20:53:23",
            "modifyTime": "2019-11-29 20:53:23",
            "state": "1"
        }
    ]
}
```

### 2.3 查询商品2

> /product/queryWithPriceRange
>
> 说明: 此接口会返回商品信息以及其所有	规格的最大,最小单价

| 属性        | 类型   | 默认值 | 必填 | 说明                                 |
| ----------- | ------ | ------ | ---- | ------------------------------------ |
| currentPage | int    | 1      | 否   | 当前页码,默认从1开始                 |
| pageSize    | int    | 10     | 否   | 每页查询数量                         |
| typeId      | int    |        | 否   | 商品类型id,如果不传入,则查询所有类型 |
| state       | String |        | 否   | 商品状态                             |

成功

```
{
	"pageSize":2,
	"currentPage":1,
	"count":28,
    "totalPage": 4,
    "products": [
        {
            "id": 28,
            "productName": "二手平板笔记本",
            "overview": "二手笔记本贱卖",
            "typeId": 1,
            "concretTypeId":1,
            "surfaceImg": "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e",
            "slideImg": null,
            "introImg": null,
            "weight": "9",
            "addTime": "2019-11-29 20:53:23",
            "modifyTime": "2019-11-29 20:53:23",
            "state": "1",
            "maxPrice": 8000.0,
            "minPrice": 4000.0
        },
        {
            "id": 56,
            "productName": "typeC数据线14",
            "overview": "高质量typeC数据线",
            "typeId": 3,
            "concretTypeId":1,
            "surfaceImg": "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e",
            "slideImg": null,
            "introImg": null,
            "weight": "8",
            "addTime": "2019-11-29 20:56:59",
            "modifyTime": "2019-11-29 20:56:59",
            "state": "1",
            "maxPrice": 18.0,
            "minPrice": 15.0
        }
    ]
}
```

### 2.3 查询商品3

> /product/getById
>
> Get
>
> 说明: 根据id来查询商品的全部信息

参数

| 属性 | 类型 | 默认值 | 必填 | 说明     |
| ---- | ---- | ------ | ---- | -------- |
| id   | int  |        | 是   | 商品的id |

成功

```
{
    "product": {
        "id": 28,
        "productName": "二手平板笔记本",
        "overview": "二手笔记本贱卖",
        "typeId": 1,
        "concretTypeId":1,
        "surfaceImg": "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e",
        "slideImg": null,
        "introImg": null,
        "weight": "9",
        "addTime": "2019-11-29 20:53:23",
        "modifyTime": "2019-11-29 20:53:23",
        "state": "1",
        "slideImgs": [
            "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e",
            "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e",
            "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e"
        ],
        "introImgs": [
            "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e",
            "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e",
            "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e",
            "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e"
        ]
    },
    "specifications": [
        {
            "id": 50,
            "productId": 28,
            "name": "mac pro 17",
            "originalPrice": 9000.0,
            "currentPrice": 8000.0,
            "inventory": 3,
            "img": "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e",
            "state": "1"
        },
        {
            "id": 51,
            "productId": 28,
            "name": "联想笔记本",
            "originalPrice": 6000.0,
            "currentPrice": 4000.0,
            "inventory": 10,
            "img": "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e",
            "state": "1"
        }
    ]
}
```

### 2.4 更新商品

> /product/update
>
> Post

参数

| 属性        | 类型   | 默认值 | 必填 | 说明                        |
| ----------- | ------ | ------ | ---- | --------------------------- |
| id          | int    |        | 是   | 商品的id                    |
| productName | String |        | 否   | 商品名称                    |
| overview    | String |        | 否   | 商品概述                    |
| surfaceImg  | String |        | 否   | 商品封面图                  |
| slideImg    | String |        | 否   | 商品轮播图                  |
| introImg    | String |        | 否   | 商品详情图                  |
| weight      | String |        | 否   | 商品权重                    |
| state       | String |        | 否   | 商品状态(1:出售,0:暂不出售) |



## 三. 购物车模块

### 3.1 添加到购物车

> /cart/addGoods
>
> 说明: 当用户在商品详情页面点击"添加到购物车"时调用此接口

参数

| 属性            | 类型 | 默认值 | 必填 | 说明       |
| --------------- | ---- | ------ | ---- | ---------- |
| specificationId | int  |        | 是   | 商品规格id |
| num             | int  |        | 是   | 添加的数量 |

### 3.2 查询购物车中商品

> /cart/get

成功

```
{
    "cart": {
        "cartMap": {
            "49": {
                "productId": 27,
                "specificationId": 49,
                "specificationImg": "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e",
                "unitPrice": 800.0,
                "totalPrice": 1600.0,
                "num": 2,
                "productName": "二手平板电脑",
                "specificationName": "联想平板",
                "addTime": "2019-12-06 09:12:56"
            },
            "47": {
                "productId": 26,
                "specificationId": 47,
                "specificationImg": "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e",
                "unitPrice": 180.0,
                "totalPrice": 360.0,
                "num": 2,
                "productName": "洗洗衣机",
                "specificationName": "部分清洗",
                "addTime": "2019-12-06 09:08:00"
            }
        },
        "allPrice": 1960.0
    }
}
```

### 3.3 修改购物车商品数量

> /cart/modifyGoodsNum
>
> 说明: 用户在购物车界面修改了商品数量后,将specificationId和num(修改后的数量)传进来

参数

| 属性    | 类型 | 默认值 | 必填 | 说明                                                         |
| ------- | ---- | ------ | ---- | ------------------------------------------------------------ |
| cart[?] |      |        | 是   | ?代表specificationId,值是修改后该规格商品的数量num;可以传多个这样的键值对 |

### 3.4 删除购物车中某种规格的商品

> /cart/deleteCartItemsBySpecificationId

参数

| 属性                | 类型 | 默认值 | 必填 | 说明                                                         |
| ------------------- | ---- | ------ | ---- | ------------------------------------------------------------ |
| specificationIds[?] | int  |        | 是   | specificationIds是一个数组或list,用来存放要删除的某种规格商品的id  ,?是从0开始的数 |

### 3.5 清空购物车

> /cart/emptyCart
>
> 说明: 将清空当前用户购物车中的所有东西

## 四. 订单模块

> /order

> **订单有以下状态state**
>
> ~~6 商品已被签收~~
>
> ~~5 派送员正在派送~~
>
> ~~3 派送员正在取件~~
>
> ~~2 已支付,且已被指派等待发货~~
>
> ~~1 已支付,但未被管理员指派或处理~~
>
> ~~0 未支付	一段时间后,该订单将变为已取消状态~~
>
> ~~-1 已取消	对于用户而言该订单不可见,对于管理员而言,该订单显示为已取消~~
>
> ~~-2 申请退款~~	
>
> ~~-3 用户已删除该订单 意味着此订单对用户不可见,但是对管理员可见,对于管理员而言,该订单仍显示为已签收~~
>
> 10 用户已确认收货且派送员已送达
>
> 9 用户已确认收货
>
> 8 派送员已送达
>
> 7 派送员正在派送
>
> 6 用户与派送员已确认取件
>
> 5 用户已确认取件
>
> 4 派送员已确认取件
>
> 3 派送员正在取件
>
> 2 已支付,且已被指派等待发货
>
> 1 已支付,但未被管理员指派或处理
>
> 0 未支付	一段时间后,该订单将变为已取消状态
>
> -1 已取消	对于用户而言该订单不可见,对于管理员而言,该订单显示为已取消
>
> -2 申请退款	
>
> -3 用户已删除该订单 意味着此订单对用户不可见,但是对管理员可见,对于管理员而言,该订单仍显示为已签收

### 4.1 订单生成

> /save
>
> 说明:
>
> 服务性商品和其他商品(如普通商品,二手商品)不要在同一个订单中
>
> 所以前端在发送生成表单请求之前判断是否同时包含服务性商品和其他商品(调用/product/getById接口查看商品类型),
>
> 价格前端发过来,与后端查出来的价格进行校验,如果对不上则报错
>
> 运费先就不考虑吧,先就传0
>
> 用户下单后,相应规格商品的库存数就会减少

| 属性                          | 类型   | 默认值 | 必填 | 说明                                                         |
| ----------------------------- | ------ | ------ | ---- | ------------------------------------------------------------ |
| addressId                     | int    |        | 是   | 用户地址Id,传地址前,请先调用/address/get接口查询出该用户的state为1的地址(state为1代表该用户的默认地址),如果用户要自行选择地址,则传入用户选择的地址id |
| pattern                       | String |        | 是   | 指配送方式/服务方式,包含'店家送上门','客户送上门','店家上门取','客户上门取' |
| paymentMethod                 | String |        | 是   | 付款方式,如微信支付                                          |
| leaveWord                     | String |        | 否   | 买家留言                                                     |
| servicingTime                 | String |        | 否   | 上门服务时间格式"yyyy-MM-dd HH:mm:ss"如"2020--1-20 09:39:00" |
| goodsAmount                   | double |        | 是   | 商品总价(所有商品的单价*数量之和)                            |
| freightCharge                 | double |        | 是   | 运费                                                         |
| actualAmount                  | double |        | 是   | 实际需要支付的价钱                                           |
| orderItems[?].specificationId | int    |        | 是   | 规格商品id                                                   |
| orderItems[?].buynum          | int    |        | 是   | 该规格商品的下单数                                           |
| orderItems[?].unitPrice       | double |        | 是   | 该规格商品的单价                                             |

成功:

```
{
    "id": "94ccafc044ba40f697e2d8f31f2adacf",
    "deliverymanId": null,
    "userId": 4,
    "addressId": 1,
    "pattern": "店家送上门",
    "paymentMethod": "微信支付",
    "leaveWord": null,
    "servicingTime": "2020-01-24 09:59:00",
    "goodsAmount": 16000.0,
    "freightCharge": 10.0,
    "actualAmount": 16010.0,
    "orderTime": "2020-01-20 10:32:52",
    "payTime": null,//订单支付的时间
    "state": "0",//订单的状态
    "orderItems": [
        {
            "id": 41,
            "orderId": "94ccafc044ba40f697e2d8f31f2adacf",
            "specificationId": 50,
            "buynum": 1,
            "unitPrice": 8000.0
        },
        {
            "id": 42,
            "orderId": "94ccafc044ba40f697e2d8f31f2adacf",
            "specificationId": 55,
            "buynum": 2,
            "unitPrice": 4000.0
        }
    ]
}
```



### 4.2 用户查询自己的订单

> /user/query 注意: 在前面加上/order,即/order/user/query
>
> 说明: 用户查询自己的所有订单

| 属性  | 类型   | 默认值 | 必填 | 说明                                                         |
| ----- | ------ | ------ | ---- | ------------------------------------------------------------ |
| state | String |        | 否   | 订单状态,不填则默认查询出该用户的全部订单(不包含已取消和已删除的订单) |

成功:

```
[
    {//这是一个订单
    	"orderId": "d4afeba6489442d0a512b994c127e8ca",
        "deliveryman": "未指定取/送人员",
        "deliverymanPhone": "",
        "goodsAmount": 16000.0,
        "freightCharge": 10.0,
        "actualAmount": 16010.0,
        "state": "0",//订单状态
        "orderItemVos": [
            {
                "id": 39,
                "orderId": "10556b73e53f4d14920dec475779b406",
                "specificationId": 50,
                "buynum": 1,
                "unitPrice": 8000.0,
                "img": "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e",
                "specificationName": "mac pro 17",
                "productId": 28,
                "productName": "二手平板笔记本"
            },
            {
                "id": 40,
                "orderId": "10556b73e53f4d14920dec475779b406",
                "specificationId": 55,
                "buynum": 2,
                "unitPrice": 4000.0,
                "img": "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e",
                "specificationName": "联想笔记本",
                "productId": 30,
                "productName": "二手平板笔记本"
            }
        ]
    },
    {//这是另一个订单
    	"orderId": "d4afeba6489442d0a512b994c127e8ca",
        "deliveryman": "未指定取/送人员",
        "deliverymanPhone": "",
        "goodsAmount": 16000.0,
        "freightCharge": 10.0,
        "actualAmount": 16010.0,
        "state": "0",
        "orderItemVos": [
            {
                "id": 37,
                "orderId": "6417e5a60e0b4b3c9649541bea95ddc9",
                "specificationId": 50,
                "buynum": 1,
                "unitPrice": 8000.0,
                "img": "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e",
                "specificationName": "mac pro 17",
                "productId": 28,
                "productName": "二手平板笔记本"
            },
            {
                "id": 38,
                "orderId": "6417e5a60e0b4b3c9649541bea95ddc9",
                "specificationId": 55,
                "buynum": 2,
                "unitPrice": 4000.0,
                "img": "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e",
                "specificationName": "联想笔记本",
                "productId": 30,
                "productName": "二手平板笔记本"
            }
        ]
    }
]
```



### 4.3 取消订单

> /cancel
>
> 说明: 用户下了单,但是还未支付时,可以取消订单,取消订单后,相应规格商品的库存数会恢复; 只有该订单的所有者才有权取消订单
>
> 参数: 
>
> orderId

### ~~4.4 删除订单~~

> ~~/delete~~
>
> ~~说明: 用户下了单,且付了款,则可删除订单,删除订单后,该订单对该用户不可见,但是对于管理员而言,仍显示为~~

### 4.5 象牙塔员工查询订单

> /staff/query 	注意: 前面记得加上/order
>
> 此接口只能获得订单的大致数据,要想知道用户具体买了哪些商品,请继续调用/order/get接口
>
> 所有象牙塔的员工都可以查询所有的订单
>
> 但是只有管理员才能指派订单

| 属性    | 类型   | 默认值 | 必填 | 说明                        |
| ------- | ------ | ------ | ---- | --------------------------- |
| state   | String |        | 否   | 订单状态,不传则默认查询所有 |
| pattern | String |        | 否   | 配送方式/服务方式           |

```
[
    {
        "id": "10556b73e53f4d14920dec475779b406",//订单id/orderId
        "deliverymanId": null,
        "userId": 4,
        "addressId": 1,
        "pattern": "店家送上门",
        "paymentMethod": "微信支付",
        "leaveWord": null,
        "servicingTime": null,
        "goodsAmount": 16000.0,
        "freightCharge": 10.0,
        "actualAmount": 16010.0,
        "orderTime": "2020-01-20 10:26:15",
        "payTime": null,
        "state": "0",
        "deliveryman": "未指定取/送人员",
        "receiverName": "钟俊杰",
        "mobile": "18000733007",
        "area": "章贡区",
        "streetAddress": "江西理工大学"
    },
    {
        "id": "e178a23348724160b31f0196ad06f393",
        "deliverymanId": null,
        "userId": 4,
        "addressId": 1,
        "pattern": "店家送上门",
        "paymentMethod": "微信支付",
        "leaveWord": null,
        "servicingTime": null,
        "goodsAmount": 16000.0,
        "freightCharge": 10.0,
        "actualAmount": 16010.0,
        "orderTime": "2020-01-20 23:24:48",
        "payTime": null,
        "state": "-1",
        "deliveryman": "未指定取/送人员",
        "receiverName": "钟俊杰",
        "mobile": "18000733007",
        "area": "章贡区",
        "streetAddress": "江西理工大学"
    }
]
```

### 4.6 根据订单id查询出详细的订单项

> /get
>
> 参数:
>
> ​	orderId 必填

成功:

```
[
    {
        "id": 40,
        "orderId": "10556b73e53f4d14920dec475779b406",
        "specificationId": 55,
        "buynum": 2,
        "unitPrice": 4000.0,
        "img": "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e",
        "specificationName": "联想笔记本",
        "productId": 30,
        "productName": "二手平板笔记本"
    },
    {
        "id": 40,
        "orderId": "10556b73e53f4d14920dec475779b406",
        "specificationId": 55,
        "buynum": 2,
        "unitPrice": 4000.0,
        "img": "https://image.suning.cn/uimg/b2c/newcatentries/0070141541-000000010553808313_1.jpg_800w_800h_4e",
        "specificationName": "联想笔记本",
        "productId": 30,
        "productName": "二手平板笔记本"
    }
]
```

### 4.7 分配订单

> /dispatch
>
> 说明: 只有管理员能分配订单
>
> 参数: 
>
> orderId	订单id 必填
>
> staffId	员工id 必填

### 4.8 派送人员查询自己需要派送的订单

> /deliveryman/query
>
> 说明: 目前所有员工都可以调用此接口,日后将完善权限管理模块,即日后只有拥有派送权限的员工可以调用此接口

成功:

```
[
    {
        "id": "d4afeba6489442d0a512b994c127e8ca",
        "deliverymanId": 5,
        "userId": 4,
        "addressId": 1,
        "pattern": null,
        "paymentMethod": "微信支付",
        "leaveWord": "这是留言",
        "servicingTime": null,
        "goodsAmount": 16000.0,
        "freightCharge": 10.0,
        "actualAmount": 16010.0,
        "orderTime": "2019-12-07 20:32:48",
        "payTime": null,
        "state": "3",
        "deliveryman": "钟大杰",
        "receiverName": "钟俊杰",
        "mobile": "18000733007",
        "area": "章贡区",
        "streetAddress": "江西理工大学"
    }
]
```

### 4.9 配送人员改变订单状态

> /deliveryman/changeOrderState
>
> 说明: 在派送人员 前往取件(3),已取件(4),前往派送(5),已签收(6) 这四种情况下,派送人员需要改变订单的状态state
>

### 4.10 用户确认收货

> /user/confirmReceived
>
> 参数:
>
> orderId 必填
>
> 说明: 只有在订单状态为7或8时才可以确认收货,请在小程序端先校验一下(后端也会校验)

### 4.11 用户确认已取货

> /user/confirmPicked
>
> 参数: 
>
> orderId 必填
>
> 说明: 只有在订单状态为3或4时才可以确认收货,请在小程序端先校验

## 五. 支付模块

> pay

### 5.1 返回支付参数(5个参数和sign)

> /order
>
> 说明:
>
> 必填参数:
>
> orderId 	订单id

成功

```
{
    "timeStamp": "1579510859601",
    "package": "prepay_id=wx20200120170059873667",
    "paySign": "7C9B24662B4B94F409DD4663D66D22E5",
    "appId": "wx8e950e75a66d8899",
    "signType": "MD5",
    "nonceStr": "f578f2f81b9640fab4d4368c1751b290"
}
```



## 六. 手机修理模块

> /repair
>
> 说明: 访问此模块下的接口都要加上这个前缀,下同
>
> 此模块可以参照学长发的那个商城

### 6.1 电器类

> /electricAppliance
>
> 说明: 访问此类下的所有接口都要加上此前缀,下同

#### 6.1.1 添加

> /save
>
> 必填参数: 
>
> ​	name

添加成功会返回刚刚插入的那条数据的id

```
{
    "id": 3
}
```



#### 6.1.2 删除

> /delete
>
> 必填参数: 
>
> ​	id

#### 6.1.3 修改

> /update
>
> 必填参数: 
>
> ​	id,name

#### 6.1.4 根据id查询

> /get
>
> 必填参数:
>
> ​	id

成功:

```
{
    "id": 1,
    "name": "手机"
}
```

#### 6.1.5 查询所有电器

> /query

成功:

```
[
    {
        "id": 1,
        "name": "手机"
    },
    {
        "id": 3,
        "name": "平板"
    }
]
```



### 6.2 品牌类

> /brand
>
> 如:苹果,华为

#### 6.2.1 添加

> /save
>
> 说明: 可以批量添加
>
> 必填参数:
>
> ​	repairBrands[?].name		
>
> ​	repairBrands[?].electricApplianceId	关联的电器的id,即调用上面电器类的/save后所得的id
>
> ​	repairBrands[?].weight  	权重,值处于1~10

#### 6.2.2 删除

> /delete
>
> 必填参数:
>
> ​	id 

#### 6.2.3 修改

> /update
>
> 必填参数:
>
> ​	id
>
> 可选参数:
>
> ​	name
>
> ​	weight

#### 6.2.4 查询某一电器的所有品牌

> /query
>
> 必填参数:
>
> ​	electricApplianceId	电器id

成功:

```
[
    {
        "id": 1,
        "name": "华为",
        "electricApplianceId": 1,
        "weight": "9"
    },
    {
        "id": 3,
        "name": "VIVO",
        "electricApplianceId": 1,
        "weight": "6"
    },
    {
        "id": 4,
        "name": "苹果",
        "electricApplianceId": 1,
        "weight": "6"
    }
]
```



#### 6.2.5 根据id查询品牌信息

> /get
>
> 必填参数:
>
> ​	id

### 6.3 机型类

> /model
>
> 如: iphonex11,vivox21...

#### 6.3.1 添加

> /save
>
> 说明: 可以批量添加
>
> 必填参数:
>
> ​	repairModels[?].name
>
> ​	repairModels[?].brandId	该机型所属的品牌
>
> ​	repairModels[?].weight	权重

#### 6.3.2 删除

> /delete
>
> 必填参数:
>
> ​	id

#### 6.3.3 修改

> /update
>
> 必填参数:
>
> ​	id
>
> 可选参数:
>
> ​	name
>
> ​	brandId
>
> ​	weight

#### 6.3.4 查询某一品牌的所有机型

> /query
>
> 说明: 不同电器的同一品牌的id是不同的,即手机的苹果和平板的苹果是不同的
>
> 必填参数:
>
> ​	brandId	品牌id

#### 6.3.5 根据id查询某一机型

> get
>
> 必填参数:
>
> ​	id

### 6.4 故障类型

> /malfunction
>
> 说明: 此接口用于给某一电器预指定一些大体的故障类型(如电池故障,显示屏故障...)		而具体的故障类型(如外屏碎,内屏碎...)则属于故障类型项,见6.5

#### ~~6.4.1 添加~~

> ~~/save~~
>
> ~~必填参数:~~
>
> ​	~~electric_appliance_id	所属的电器的id~~
>
> ​	~~repairMalfunctions[?].name 大体故障名称  可批量添加~~
>
> ~~说明: 这里我设计将故障类型与电器相关联,即给电器预先添加一些大体的故障类型,这样一来在给不同机型添加具体的故障类型项的时候可以避免数据冗余~~

#### 6.4.2 删除

> /delete
>
> 必填参数:
>
> ​	id

#### 6.4.3 修改

> /update
>
> 必填参数:
>
> ​	id
>
> ​	name

#### ~~6.4.4 根据电器id查询出所有故障类型~~

> ~~/query~~
>
> ~~必填参数:~~
>
> ​	~~applianceId		电器id~~

#### 6.4.5 根据id查询

> /get
>
> 必填参数:
>
> ​	id

#### 6.4.6 根据机型id查询出所有故障类型以及故障类型项

> /queryWithItems
>
> 必填参数 :
>
> ​	modelId

```
[
    {
        "id": 2,
        "name": "电池充电类",
        "modelId": 1,
        "repairMalfunctionItems": [
            {
                "id": 4,
                "itemName": "电池",
                "originalPrice": 110.0,
                "currentPrice": 100.0,
                "malfunctionId": 2
            },
            {
                "id": 5,
                "itemName": "充电接口",
                "originalPrice": 89.0,
                "currentPrice": 80.0,
                "malfunctionId": 2
            }
        ]
    },
    {
        "id": 3,
        "name": "屏幕故障(旧屏需回收)",
        "modelId": 1,
        "repairMalfunctionItems": [
            {
                "id": 2,
                "itemName": "内屏碎(显示触摸异常)",
                "originalPrice": 300.0,
                "currentPrice": 280.0,
                "malfunctionId": 3
            },
            {
                "id": 3,
                "itemName": "内屏碎",
                "originalPrice": 300.0,
                "currentPrice": 280.0,
                "malfunctionId": 3
            }
        ]
    }
]
```



### 6.5 故障类型项

> ```
> /malfunctionItem
> ```

#### 6.5.1 添加

> /save
>
> 必填参数:
>
> ​	itemName	
>
> ​	malfuncationId 	故障类型id
>
> ​	originalPrice		原价
>
> ​	currentPrice		现价
>
> ​	modelId				机型id

#### 6.5.2 删除

> delete
>
> 必填参数:
>
> ​	id

#### 6.5.3 修改

> /update
>
> 必填参数:
>
> ​	id
>
> 可选参数:(至少要传一个)
>
> ​	itemName
>
> ​	malfuncationId
>
> ​	originalPrice
>
> ​	currentPrice

#### 6.5.4 根据机型id查询所有故障类型和具体的故障项

> /queryWithItems
>
> 必填参数:
>
> ​	modelId		机型id

成功:

```
[
    {
        "id": 3,
        "name": "屏幕故障(旧屏需回收)",
        "electricApplianceId": null,
        "repairMalfunctionItems": [
            {
                "id": 2,
                "itemName": "内屏碎(显示触摸异常)",
                "originalPrice": 300.0,
                "currentPrice": 280.0,
                "malfunctionId": 3,
                "modelId": 8
            },
            {
                "id": 3,
                "itemName": "内屏碎",
                "originalPrice": 300.0,
                "currentPrice": 280.0,
                "malfunctionId": 3,
                "modelId": 8
            }
        ]
    },
    {
        "id": 2,
        "name": "电池充电类",
        "electricApplianceId": null,
        "repairMalfunctionItems": [
            {
                "id": 4,
                "itemName": "电池",
                "originalPrice": 110.0,
                "currentPrice": 100.0,
                "malfunctionId": 2,
                "modelId": 8
            },
            {
                "id": 5,
                "itemName": "充电接口",
                "originalPrice": 89.0,
                "currentPrice": 80.0,
                "malfunctionId": 2,
                "modelId": 8
            }
        ]
    }
]
```



## 七. 员工模块

> /staff
>
> 说明: 管理员,派送员都属于此模块
>
> 员工与用户不属于同一张表

### 7.1 登录

> /login
>
> 说明:
>
> 管理员->username:zjj	password:123
>
> 配送员->username:staff2 password:123
>
> 必填参数:
>
> ​	username
>
> ​	password

### 7.2 添加新员工

> /add
>
> 说明:
>
> ​	还不完善,只有管理员能添加新员工
>
> 必填参数:
>
> ​	username
>
> ​	password
>
> ​	realName	员工真实姓名
>
> ​	phone	员工手机号
>
> ​	role	员工角色,暂时只有<u>管理员</u>和<u>配送员</u> 后期将要完善权限管理系统

## 八. 地址

> /address

### 8.1 添加地址

> /save
>
> 必填参数:
>
> ​	receiverName  收货人姓名
>
> ​	mobile				手机号
>
> ​	area					所在区域(指较大范围,如章贡区)
>
> ​	streetAddress	所在街道(指较小范围,如江西理工大学红旗校区3栋...)

### 8.2 用户查询自己的所有地址

> /query

成功:

```
[
    {
        "id": 5,
        "receiverName": "钟大杰",
        "mobile": "18122211121",
        "area": "章贡区",
        "streetAddress": "江西理工大学红旗校区",
        "userId": 4,
        "state": "0"
    },
    {
        "id": 6,
        "receiverName": "钟小杰",
        "mobile": "18000733008",
        "area": "江西省吉安市泰和县",
        "streetAddress": "泰发花园",
        "userId": 4,
        "state": "1"
    }
]
```

### 8.3 删除地址

> /delete
>
> 说明: 删除其实是把该地址的状态改为-1
>
> 必填参数:
>
> ​	addressId 	地址id

### 8.4 更新地址

> /update
>
> 必填参数:
>
> ​	id		地址id
>
> 可选参数
>
> ​	mobile	手机号
>
> ​	area
>
> streetAddress

### 8.5 根据addressId查询地址

> /get
>
> 必填参数:
>
> ​	addressId

成功

```
{
    "id": 5,
    "receiverName": "钟大姐",
    "mobile": "18122211121",
    "area": "章贡区",
    "streetAddress": "江西理工大学红旗校区",
    "userId": 4,
    "state": "0"
}
```

### 8.6 用户设置其某个地址为默认地址

> /setToDefault
>
> 说明: 
>
> ​	同一个用户只能有一个默认地址,如果设置另一个地址为默认,则之前的默认地址变为非默认
>
> 必填参数:
>
> ​	addressId

### 8.7 用户查询其默认地址

> /getDefaultAddress

成功

```
{
    "id": 6,
    "receiverName": "钟小姐",
    "mobile": "18000733008",
    "area": "江西省吉安市泰和县",
    "streetAddress": "泰发花园",
    "userId": 4,
    "state": "1"
}
```

## 九. 首页轮播图和广告

> /homepage/picture

### 9.1 查询

> /queryAll
>
> 参数: 
>
> ​	type ->1:轮播图,2:广告  非必填
>
> ​	state->1:发布,0不发布   非必填
>
> 

## 十. 让修理店联系用户

> /waitforcall/save
>
> 参数:
>
> ​	name 用户姓名(用户自填)
>
> ​	phone 用户手机号
>
> ​	malfunctionItemId 用户选中的故障类型项id
>
> ​	merchantId 商户id