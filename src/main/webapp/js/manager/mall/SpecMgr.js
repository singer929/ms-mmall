/**
 * Created by Administrator on 2016/12/19.
 */

var SpecMgr = {
    productId: -1,
    specs: null,
    productSpecs: null,
    specDetails: null,
    detailMap: null,

    init: function (productId, dataStr) {

        var data = JSON.parse(dataStr);
        if (!data) return false;

        this.initSpecs(data.specs);

        this.initProductSpecs(data.productSpecs);

        this.initSpecDetails(data.productSpecDetails);

        console.log(this.specs);
        console.log(this.productSpecs);
        console.log(this.specDetails);

        return true;
    },

    // 初始化规格配置
    initSpecs: function (arr) {
        this.specs = {};

        for (var i in arr){
            var spec = arr[i];
            this.specs[spec.specificationId] = spec;
        }
    },

    // 初始化商品规格数据
    initProductSpecs: function (specArr) {

        this.productSpecs = {};

        for (var i in specArr){
            var specData = specArr[i];
            var specId = specData.specId

            if (!this.productSpecs[specId]){
                this.productSpecs[specId] = [];
            }

            this.productSpecs[specId].push(specData);
        }
    },

    // 初始化规格明细数据
    initSpecDetails: function (arr) {

        for (var i in arr){
            var detail = arr[i];
            var valuesStr = detail.specValues;
            var valuesObj = {};

            var valueArr = valuesStr.split(',');
            for (var j in valueArr){

                var valueStr = valueArr[j];
                var data = valueStr.split(':');
                var specId = data[0];
                var specValue = data[1];
                valuesObj[specId] = specValue;
            }
            // obj数据覆盖字符串数据
            detail.specValues = valuesObj;
        }

        this.specDetails = arr;

        this.setGridMap(arr);
    },

    // 设置表格用的Map 树形结构
    setGridMap: function (details) {

        this.detailMap = {};
        for (var i in details) {

            var detail = details[i];

            var len = this.getObjLength(detail.specValues);

            var tmpObj = this.detailMap;
            var index = 0;
            for (var specId in detail.specValues){
                var specValue = detail.specValues[specId];

                var key = specId + ':' + specValue;

                tmpObj = tmpObj || {};
                if (index == len - 1) {
                    tmpObj[key] = detail.detailId || -1;
                }
                else{
                    tmpObj[key] = tmpObj[key] || {};
                    tmpObj = tmpObj[key];
                }

                index ++;
            }
        }
    },

    // 根据规格id获取规格的配置
    getSpecConfigById: function (id) {
        return this.specs[id];
    },

    // 根据规格名称获取规格配置
    getSpecConfigByName: function(name) {

        for (var i in this.specs){
            var spec = this.specs[i];
            if (spec.name == name) return spec;
        }
        return null;
    },

    // 根据id获取规格明细数据
    getDetailData: function (id) {
        for (var i in SpecMgr.specDetails){
            var detail = SpecMgr.specDetails[i];
            if (detail.detailId == id){
                return detail;
            }
        }

        return null;
    },

    // 根据规格明细数据的键值获取数据
    getDetailDataByKeys: function (keys) {

        var obj = this.keysToObj(keys);
        for (var i in this.specDetails){
            var specDetail = this.specDetails[i];
            if (objEqual(specDetail.specValues, obj)){
                return specDetail;
            }
        }

        return null;
    },

    // 根据specId和specValue 删除明细数据
    deleteDetailData: function (specId, specValue) {

        //var specValue = arguments[1];

        var newDetails = [];
        for (var i in this.specDetails){
            var specDetail = this.specDetails[i];
            var find = false;
            for (var spcId in specDetail.specValues){
                if (spcId != specId) continue;
                if (specDetail.specValues[spcId] == specValue){
                    find = true;
                    break;
                }
            }
            if (!find){
                newDetails.push(specDetail);
            }
        }
        this.specDetails = newDetails;

        this.setGridMap(this.specDetails);
    },

    keysToObj: function(keys){
        var obj = {};
        var mapStrArr = keys.split(',');
        for (var i in mapStrArr){
            var mapStr = mapStrArr[i];
            var mapArr = mapStr.split(':');
            obj[mapArr[0]] = mapArr[1];
        }
        return obj;
    },

    objToKeys: function(obj){
        var keys = '';
        for (var specId in obj){
            var specValue = obj[specId];
            keys += (keys ? ',' : '') + specId + ':' + specValue
        }

        return keys;
    },

    // 构建服务器数据
    buildSpecSvrData: function () {
        // 构建服务器产品规格数据
        var productSpecArr = [];
        for (var specId in this.productSpecs){
            var psArr = this.productSpecs[specId];
            for (var i in psArr){
                productSpecArr.push(psArr[i]);
            }
        }

        var svrProductSpecArr = [];
        for (var i in productSpecArr){
            var ps = productSpecArr[i];
            svrProductSpecArr.push({
                img: ps.img,
                productId: ps.productId,
                specId: ps.specId,
                specValue: ps.specValue
            });
        }

        //构建服务器商品规格明细数据
        var svrSpecDetailArr = [];
        for (var i in this.specDetails){
            var detail = this.specDetails[i];
            svrSpecDetailArr.push({
                productId: detail.productId,
                specValues: this.objToKeys(detail.specValues),
                stock: detail.stock,
                price: detail.price,
                sale: detail.sale,
                code: detail.code,
                sort: 0
            });
        }

        return {productSpecList:svrProductSpecArr, detailList:svrSpecDetailArr};
    },

    // 计算Object的长度 有多少个key
    getObjLength: function (obj) {

        var c = 0;
        for (var i in obj){
            c ++;
        }
        return c;
    },

    // 规格配置
    addSpec: function(name, id) {
        id = id || new Date().getTime();
        var spec = {name: name, specificationId: id};
        this.specs[id] = spec;
        return spec;
    },

    // 添加商品规格
    addProductSpec: function () {

    },

    // 删除产品规格数据
    deleteProductSpecBySpecId: function (specId){
        var specValue = arguments[1];

        if (!specValue){
            for (var spId in this.productSpecs){
                if (specId != spId) continue;
                delete this.productSpecs[spId];
                this.rebuildDetails();
                break;
            }
        }
        else {
            for (var spId in this.productSpecs){
                if (specId != spId) continue;
                var psArr = this.productSpecs[spId];
                for (var i in psArr){
                    if (psArr[i].specValue == specValue){
                        psArr.splice(i, 1);
                        if (psArr.length) {
                            this.deleteDetailData(specId, specValue);
                        }
                        else{
                            delete this.productSpecs[spId];
                            this.rebuildDetails();
                        }

                        return;
                    }
                }
            }

        }
    },

    // 是否有此规格名
    hasSpec: function (name) {

        for (var specId in this.specs) {
            var spec = this.specs[specId];
            if (spec.name == name) return true;
        }

        return false;
    },

    // 根据产品规格数据 生成 明细数据的结构
    rebuildDetails :function () {

        var clean = arguments[0] || true;

        // 保存临时数据
        if (!clean){
            var oldDetails = [];
            for (var i in this.specDetails){
                oldDetails.push(this.specDetails[i]);
            }
        }

        this.specDetails = [];
        var details = this.specDetails;

        var specArr = [];
        for (var i in this.productSpecs){
            specArr.push(this.productSpecs[i]);
        }

        if (!specArr.length) {
            this.setGridMap(this.specDetails);
            return;
        }

        setData({}, specArr, 0);

        // 若不清除数据 将原来数据存回来
        if (!clean){
           for (var i in oldDetails){
               var oldDetail = oldDetails[i];
               for (var j in this.specDetails){
                   var detail = this.specDetails[j];
                   if (objEqual(detail.specValues, oldDetail.specValues)){

                       for (var key in oldDetail){
                           detail[key] = oldDetail[key];
                       }
                       break;
                   }
               }
           }
        }

        this.setGridMap(this.specDetails);

        function setData(datas, arr, i){

            if (i < arr.length){

                for (var j in arr[i]){
                    datas[arr[i][j].specId] = arr[i][j].specValue;
                    setData(datas, arr, i+1);
                }
            }
            else{
                details.push({specValues:deepClone(datas)});
            }
        }
    }
}