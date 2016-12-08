//specifications.init(products,$("#data")); 调用
var specifications = {
	json : {}, // 规格数据
	totalRow : 1, // 总行数
	head : new Array(),
	data : new Array(),
	target : null, // 显示区域表格
	init : function(data, target) {
		this.json = data;
		this.target = target;
		this.total(this.json);
		//this.totalRow = this.totalRow * products.length;
		this.readHead(this.json, 0);
		this.showHead();
		this.initTable();
		this.initMerge(0, this.json, this.json.length);
		this.readData();
	},
	readHead : function(_json, level) { // 解析头部标题
		// 规格实体,id:规格编号 title:规格名称
		function Specifications(id, title, level) {
			this.id = id;
			this.title = title;
			this.level = level;
		}
		//this.head.push(new Specifications(_json[0].specifications.specificationsId, _json[0].specifications.specificationsName, level));
		this.head.push(new Specifications(_json[0].specificationsId, _json[0].specificationsName, level));
		if (this.hasChilds(_json[0])) {
			this.readHead(_json[0].childProductSpecifications, ++level);
		}

	},

	total : function(_json) {
		// 每一层相乘＝节点总数 =
		for (i = 0; i < _json.length; i++) {
			if (this.hasChilds(_json[i])) {
				this.totalRow = this.totalRow * _json[i].childProductSpecifications.length;
				this.total(_json[i].childProductSpecifications);
			}
		}

	},
	showHead : function() { // 显示头部信息
		var rowHead = $("<tr></tr>");
		for (i = 0; i < this.head.length; i++) {
			rowHead.append("<td data-level='" + this.head[i].level + "'>" + this.head[i].title + "</td>");
		}
		rowHead.append("<td>费率</td>");
		this.target.append(rowHead);
	},
	initTable : function(json) { // 布局表格，显示行数据
		for (i = 0; i < this.totalRow; i++) {
			var tr = $("<tr class='row-" + i + "'></tr>");
			for (k = 0; k < this.head.length; k++) {
				tr.append("<td class='row-" + i + " col-" + k + "'>行" + i + ":列" + k + "</td>");
			}
			tr.append("<td></td>");
			this.target.append(tr);
		}
	},
	// 合并
	// 从第一列开始合并,
	// 合并数量=总行数/第一级规格总数
	// 处理行数=第一级规格总数
	// col:合并列位置
	// row:处理行数
	// mergeRow:合并行数
	merge : function(col, row, mergeRow) {
		// 处理行数
		for (i = 0; i < row; i++) {
			$(".row-" + (mergeRow * i) + " .col-" + col).attr("rowspan", mergeRow);
			for (k = (mergeRow * i + 1); k < (mergeRow * i + mergeRow - 1) + 1; k++) {
				$(".row-" + k + " .col-" + col + "").remove();
			}
		}

	},
	readData : function(l) {

		for (var y = 0; y < this.head.length; y++) {
			switch (y) {
			case 0:
				for (var u = 0; u < this.json.length; u++) {
					$(".col-" + y + ":eq(" + u + ")").text(this.json[u].specificationsField);
				}
				break;
			case 1:
				for (var u = 0; u < this.json.length; u++) {
					for (var u1 = 0; u1 < this.json[u1].length; u1++) {
						$(".col-" + y + ":eq(" + u + ")").text(this.json[u].specificationsField);
					}
				}				
				break;
			}

		}

	},
	// level：合并列层级
	// _json：json 数据格式
	// count：上一级数量
	initMerge : function(level, _json, count) {
		if (level > 0) {// 如果大于第一级就需要
			console.log("合并:" + level + "," + (_json.length * count) + "," + this.totalRow / (_json.length * count));
			this.merge(level, _json.length * count, this.totalRow / (_json.length * count));

		} else { // 第一级
			console.log("合并:" + level + "," + _json.length + "," + (this.totalRow / _json.length));
			
			this.merge(level, _json.length, this.totalRow / _json.length);
		}
		if (this.hasChilds(_json[0])) {
			if (level > 0) {
				this.initMerge(++level, _json[0].childProductSpecifications, count * _json.length);
			} else {
				this.initMerge(++level, _json[0].childProductSpecifications, count);
			}

		}
	},

	// 判断是否有下一个节点
	hasChilds : function(_json) {
		return typeof _json != "undefined" && _json.childProductSpecifications.length > 0;
	},
}
