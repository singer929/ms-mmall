 function DY_scroll(wraper,prev,next,img,speed,or)
 { 

  var wraper = $(wraper);
  var prev = $(prev);
  var next = $(next);
  var img = $(img).find('ul');
  var w = img.find('li').outerWidth(true);
  var s = speed;
  
  next.click(function()
       {
       
        img.animate({'margin-left':-w},function()
          {
           img.find('li').eq(0).appendTo(img);
           img.css({'margin-left':0});
           });
        });
  prev.click(function()
       {
        img.find('li:last').prependTo(img);
        img.css({'margin-left':-w});
        img.animate({'margin-left':0});
        });

  //hover
  img.find("li").hover(function(){
    $("#big_img").attr("src",$(this).find("img").attr("hurl"));
    $("#big_img").attr("jqimg",$(this).find("img").attr("url"));
  });

  //是否自动滚动
  if (or == true)
  {
   ad = setInterval(function() { next.click();},s*10);
   wraper.hover(function(){clearInterval(ad);},function(){ad = setInterval(function() { next.click();},s*1000);});

  }
 }