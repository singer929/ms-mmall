/**
 * Created by Administrator on 2016/12/19.
 */

var PluploaderMgr = {
	
	instances: {},
	
	url: "",
	basePath: "",
	path: "",
	maxSize: 1,
	fileType: "*.jpg;*.png;*.gif;*.bmp;*.jpeg",
	isRename: true,
	
	init: function(params){
		if (params.url) this.url = params.url;
		if (params.basePath) this.basePath = params.basePath;
		if (params.path) this.path = params.path;
		if (params.maxSize) this.maxSize = params.maxSize;
		if (params.fileType) this.fileType = params.fileType;
		if (params.isRename) this.isRename = params.isRename;
	},
	
	//创建上传器实例
	createInstance: function(triggerId, imgId, successCallback){
		
		var uploader = new plupload.Uploader({
			runtimes: 'html5',
			browse_button: triggerId,
			url: this.url,
			filters: {
				max_file_size: this.maxSize+'mb',
				mime_types: [{title:"ImageFiles", extensions:"jpg,gif,png,bmp,jpeg"}]
			},
			// 上传服务器数据
			multipart_params: {
				uploadPath: this.path,
				isRename: this.isRename,
				maxSize: this.maxSize,
				allowedFile: this.fileType
			}
		});
		
		this.instances[triggerId] = uploader;
		
		uploader.init();
		//初始化触发
		uploader.bind('Init', function(up){
			
		});
		// 用户添加文件事件: 直接触发上传
		uploader.bind('FilesAdded', function(up, files){
			uploader.start();
		});
		
		// 队列中所有文件被上传完
		uploader.bind('UploadComplete', function(up, file){
			
		});
		
		// 当一个文件开始上传的时候触发
		uploader.bind('UploadFile', function(up, file){
			
		});
		
		// 文件上传成功的时候触发
		uploader.bind('FileUploaded', function(up, file, result){
		
			if (result.status == 200){
				// 图片的服务器路径
				var imgUrl = result.response;
				$('#'+imgId).attr('src', basePath + '/' + imgUrl);
			}
			else{
				alert('上传失败，请求返回码:' + result.status);
			}
			
			if (successCallback != null){
				successCallback(result);
			}
		});
	},
	
	// 销毁实例
	destoryInstance: function(triggerId){
		var uploader = this.instances[triggerId];
		if (uploader){
			uploader.destory();
		}
	}
}