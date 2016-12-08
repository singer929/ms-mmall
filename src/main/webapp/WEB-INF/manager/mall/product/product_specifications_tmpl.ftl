				<!--规格下拉框-->
				<script id="specificationsSelectTmp" type="text/x-jquery-tmpl"> 
					<#noparse>
			    		<select class="specificationsSelect " name="specificationsName" style="width:200px">
			    				<option value="">请选择商品规格</option>
			    				 {{each(i,s) list}}
			    				 	<option value="${s.specificationsId}" data-field=${s.specificationsField}>${s.specificationsName}</option>
			    				  {{/each}}
			    		</select>
			    		<div class="removeField">删除</div>
					</#noparse>					
				 </script>		
				 <!--规格里面的添加按钮-->	
				 <script id="specificationsSelectButtonTmp" type="text/x-jquery-tmpl"> 
							<div class="row selectRow" style="position: relative;">
								<div class="specificationsSelectPanel">
									<button type="button" class="btn btn-default addSpecificationsButton">添加商品规格</button>
								</div>
								<div class="row selectValueDiv"  style="margin:10px 0 8px 0;">
								</div>			
							</div>
				 </script>
				 
				 <!--增加规格按钮-->
				 <script id="specificationsAddButtonTmp" type="text/x-jquery-tmpl"> 
						<div class="col-md-1 addSpecificationsFieldButton" data-container="body" data-toggle="popover" data-placement="bottom" data-content="" style="maring:0;padding:0;float: left;"><button type="button" class="btn btn-default">+	</button></div>	
				 </script>		
				 <!--增加规则值下拉框按钮-->
				 <script id="specificationsFieldsSelectTmp" type="text/x-jquery-tmpl"> 
				 		<div  style="margin:0;padding:0;width: 250px;float: left;">
							<input type='hidden' class='select2FieldValues' />
						</div>
						<div   style="padding-right: 0;width: 128px;float: left;text-align: right;" >
							<button type="button" class="btn btn-primary specificationsFieldsOk">确定	</button>
							<button type="button" class="btn btn-default specificationsFieldsCancel">取消	</button>
						</div>
				 </script>
				 <script  id="specificationsValuesTmp" type="text/x-jquery-tmpl"> 
				    <#noparse>
					 <div class=" fieldValueDiv" style="padding:0;float: left;" >
							<div class="values" >
								<span>${val}</span>
								<div class="remove">×</div>
								<i class="img">{{if  img!="undefined"}}<img src="${img}"/>{{/if}}</i>
								<em class="img"></em>
							</div>			 
					 </div>	
					 </#noparse>
				 </script>			 		 
	
					<!--规格动态行数据-->
						<script id="specificationsRow" type="text/x-jquery-tmpl"> 
							<#noparse>
							<div class="row specifications-row " data-id="${specificationsId}"   data-level="0"  style="border-top:none;border-left: none;">
										<div class="text-center specifications-value col-md-1" style="padding: 0;;width:100px;float: left;" data-level="0"  data-id="${specificationsId}">
											<ul>
												<li data-text="${specificationsField}" class="title ms-text-hide" style="width:100px">
												${specificationsField}
												</li>
											</ul>
										</div>
										<div class="col-md-7 specifications-row-input" style="padding: 0;" data-text="${specificationsField}"  >
											<!--输入框位置-->
										</div>												
							</div>	
							</#noparse>									
						 </script>
						 <!---->
						<script id="specificationsNewRow" type="text/x-jquery-tmpl"> 
							<#noparse>
								<div class="row specifications-row " data-id="${specificationsId}"   data-level="${level}"  style="border-top:none;">
											<div class="col-md-7 specifications-row-input" style="padding: 0;width:100px;float: left;" data-id="${specificationsId}"  >
												<!--输入框位置-->
											</div>												
								</div>	
							</#noparse>									
						 </script>						 
						 
						 
						 <!--每增加一个小的规格都需要增加一组输入框-->
						<script id="specificationsOneRow" type="text/x-jquery-tmpl"> 
							<#noparse>
											<div class="row" style="border-bottom: 1px solid #ccc;paddin:0;;width: 402px;float: left;"> 
													<div class="text-center col-md-3  " style="padding: 5px 0 4px 8px;float: left;width:100px"><input type="text"  class=" form-control input-sm specificationsPrice" data-id="${productSpecificationsId}" value="${specificationsPrice}" maxlength ="9"  data-ms-validator="{required:true,min:0.01,max:999999999,range:true,message:{required:'不为空',range:'最小0.01最大999999999'}}"/></div>
													<div class="text-center col-md-3 " style="padding:5px 0 4px 8px;float: left;width:100px"><input type="text" class=" form-control input-sm specificationsInvertory" value="${specificationsInvertory}" maxlength ="9" data-ms-validator="{required:true,min:0,max:999999999,range:true,message:{required:'不为空',range:'最大999999999'}}"/></div>
													<div class="text-center col-md-3 " style="padding: 5px 0 4px 8px;float: left;width:100px"><input type="text"  class=" form-control input-sm productSpecificationsInventoryCode" value="${productSpecificationsInventoryCode}" maxlength ="9" /></div>
													<div class="text-center col-md-3" style="padding: 5px 0 4px 8px;float: left;border-right: 1px solid #ccc;width:101px"><input type="text" readonly  class=" form-control input-sm" value="{{if specificationsSale}}${specificationsSale}{{else}}0{{/if}}" /></div>										
											</div>
							</#noparse>									
						 </script>						 
						 
						 <!--标题字段-->
						<script id="specificationsField" type="text/x-jquery-tmpl"> 
							<#noparse>
							<div class="text-center col-md-1 text-vcenter specifications-field text-overflow" style="border: 1px solid #ccc;border-left: none;float: left;width:100px" data-id="${specificationsId}" data-level="${level}">${title}</div>	
							</#noparse>					
						 </script>			
						 <!--具体的规格字，完全是新创建-->
						<script id="specificationsValuesUl" type="text/x-jquery-tmpl"> 
							<#noparse>
							<div class="text-center col-md-1 text-vcenter  specifications-value" data-id="${specificationsId}" data-level="${level}" style="display: inline-table;padding: 0; border-right: 1px solid #ccc;border-right: none;float: left;width:100px">
								{{each size}}
								<ul>
								</ul>
								{{/each}}  
							</div>	
							</#noparse>					
						 </script>		
						 <!--具体的规格值，已经存在一部分，逐个增加-->
						<script id="specificationsValuesLi" type="text/x-jquery-tmpl"> 
							<#noparse>
								<li  class="title ms-text-hide"  data-text="${specificationsField}" style="width:100px">
								${specificationsField}
								</li>									
							</#noparse>					
						 </script>		
				<!-- 用户选择商品规格开始 -->
				<div class="form-group">
					<label class="col-md-2 control-label col-xs-3">商品规格:</label>
					<div class="col-md-7 col-xs-8  addSpecificationsPanel" style="margin:0;padding:0"> 
						<div class="row" id="specificationsSelectPanel">
							<div class="row selectRow"  style="position: relative;">
								<div class="specificationsSelectPanel">
									<button type="button" class="btn btn-default addSpecificationsButton">添加商品规格</button>
								</div>
								<div class="row selectValueDiv"  style="margin:10px 0 8px 0;">
								</div>
							</div>

						</div>
					</div>
				</div>
				<!-- 用户选择商品规格结束 -->						 
					<!-- 商品库存开始 -->
				<div class="form-group inventoryTable" style="display:none">
					<input type="hidden" name="specificationsId">
					<label class="col-md-2 control-label col-xs-3"><em class="required">*</em>商品库存:</label>
					<div class="col-md-9 col-xs-9" style="margin:0;padding:0"> 
					
						<div id="specificationsList" style="width:1300px">
							<div class="row specificationsList-title"  style="line-height: 38px;border-bottom:1px;">
										<div class="col-md-7 specifications-list-title" style="padding: 0;">
											<div class="text-center col-md-3  " style="border: 1px solid #ccc;border-left:none;border-right: 1px solid #ccc;width:100px;float: left;">价格(元)</div>
											<div class="text-center col-md-3" style="border: 1px solid #ccc;border-left:none;border-right: 1px solid #ccc;width:100px;float: left;">库存</div>
											<div class="text-center col-md-3" style="border: 1px solid #ccc;border-left:none;border-right: 1px solid #ccc;width:100px;float: left;">商家编码</div>
											<div class="text-center col-md-3" style="border: 1px solid #ccc;border-left:none;width: 126px;width:101px;float: left;">销量</div>										
										</div>
							</div>
	
													
						</div>		
										
					</div>
				</div>				
				<!-- 商品库存结束 -->						 