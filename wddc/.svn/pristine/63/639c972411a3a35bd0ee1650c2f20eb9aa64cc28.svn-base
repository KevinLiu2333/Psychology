//================================插件的开发方式===============================
//（1）编写插件弹出窗口的html文件，文件名规则：dream-[控件类型].html
//（2）$\ueditor\themes\default\images\icons.gif和$\ueditor\themes\default\images\icons.png增加图标
//（3）修改dream.form.css，主要是button图标位置和弹出窗口大小
//（4）在myplugins数组中增加插件[插件名称,插件标题,插件类型]，插件名称规则和html文件规则雷同：dream-[控件类型]，
//     插件标题主要用于toolbar中的button提示和弹出框标题，插件类型主要指在ueditor中可自动识别的类型见line93
//=============================================================================

(function () {
    
    UE.registerUI('wordOrExcelHtmlCleaner', function(editor, uiName) {                
        //创建一个button
        var btn = new UE.ui.Button({
            //按钮的名字
            name: uiName,
            //提示
            title: uiName,
            //添加额外样式，指定icon图标，这里默认使用一个重复的icon
            cssRules: 'background-position: -200px -139px;',
            //点击时执行的命令
            onclick: function() {
                if(confirm("清理命令仅用于word或excel转换的html，确认清理吗？")){
                    editor.setContent(wordOrExcelHtmlCleaner(editor.getContent()));
                }
            }
        });
        
        return btn;
    });
                
    var myplugins = [        
        ['dream-text', '单行录入框', 'input'],
        ['dream-password', '密码域', 'input'],
        ['dream-hidden', '隐藏域', 'input'],
        ['dream-date', '日期域', 'input'],
        ['dream-select', '下拉列表框', 'select'],
        ['dream-textarea', '多行录入框', 'textarea'],
        ['dream-subtable', '子表编辑', 'subtable'],
        ['dream-multiple','复选框','input']
    ];
    
    for (var p in myplugins) {
        (function (p) {
            //在toolbar上注册按钮
            UE.registerUI(p[0],function(editor,uiName){
                var btn = new UE.ui.Button({
                    //按钮的名字
                    name:uiName,
                    //提示
                    title:p[1],
                    //需要添加的额外样式，指定icon图标，这里默认使用一个重复的icon
                    className:p[0],
                    //点击时执行的命令
                    onclick:function () {
                        //这里可以不用执行命令,做你自己的操作也可
                        editor.execCommand(uiName);
                    }
                });
            
                //因为你是添加button,所以需要返回这个button
                return btn;
            });
            
            //创建插件
            UE.plugins[p[0]] = function () {
            	var me = this;
            	    
            	me.commands[p[0]] = {
            		execCommand:function () {
            			var dialog = new UE.ui.Dialog({
            				iframeUrl:this.options.UEDITOR_HOME_URL + 'dreamform/' + p[0] + '.html',
            				editor:this,
            				name:p[0],
            				title: p[1],
            				className:p[0],
            				buttons:[
            				{
            					className:'edui-okbutton',
            					label:'确定',
            					onclick:function () {
            						dialog.close(true);
            					}
            				},
            				{
            					className:'edui-cancelbutton',
            					label:'取消',
            					onclick:function () {
            						dialog.close(false);
            					}
            				}]
            			});
            			dialog.render();
            			dialog.open();
            		}
            	};
            	
            	var popup = new UE.ui.Popup( {
            		editor:this,
            		content: '<nobr>' + p[1] + ': <span onclick=$$._edittext() class="edui-clickable">编辑</span>&nbsp;&nbsp;<span onclick=$$._delete() class="edui-clickable">删除</span></nobr>',
            		className: 'edui-bubble',
            		_edittext: function () {
            			  UE.plugins[p[0]].editdom = popup.anchorEl;//获得当前对象
            			  me.execCommand(p[0]);
            			  this.hide();
            		},
            		_delete:function(){
            			if( window.confirm('确认删除该控件吗？') ) {
            				UE.dom.domUtils.remove(this.anchorEl,false);
            			}
            			this.hide();
            		}
            	} );
            	popup.render();
            	
            	me.addListener( 'mouseover', function( t, evt ) {
            		evt = evt || window.event;
            		var e1 = evt.target || evt.srcElement;
                    var pluginName = e1.getAttribute('dmPlugin');
            		if ( eval('/'+p[2]+'|span/ig').test( e1.tagName ) && pluginName == p[0]) {
            		    if(e1.tagName == 'SPAN'){
                            var e1Input = e1.getElementsByTagName(p[2]);
                            e1 = e1Input.length > 0 ? e1Input[0] : e1;
                        }
            			popup.anchorEl = e1;//锚定当前对象
            			popup.showAnchor( popup.anchorEl );
            		}
            	});
            };
        	
        })(myplugins[p]);
    }
})();

(function($){
    //把form序列化成对象，name相同的表示为数组
    $.fn.serializeObject=function(){
        var obj={};   
        var array=this.serializeArray(); 
        
        $(array).each(function(){
            if(obj[this.name]){//对象是否存在
                if($.isArray(obj[this.name])){//是否是数组
                    obj[this.name].push(this.value);
                }else{
                    obj[this.name]=[obj[this.name],this.value];//创建数组
                }
            }else{
                obj[this.name]=this.value;//创建对象
            }
        });
        return obj;
    };   
})(jQuery);

//===============================================================     
var max_len = 8;   //最大行数   
function addtr(){
    var len=$("#subTable tr").length;
    
    if(len < max_len + 1){   
        $("#cloneRow").clone().appendTo($("#subTable"));
    }  
}

function deltr(ctr){   
    var len=$("#subTable tr").length;   
    if(len <= 2){   
        alert("请至少保留一行，作为增加时复制行使用");   
    }else{   
        $(ctr).parent().parent().remove();//button->td->tr
    }   
}    
//===============================================================  

//清除word或excel转成的html
function wordOrExcelHtmlCleaner( html ){
    // Remove all B tags
    html = html.replace(/<\/?B[^>]*>/gi, "" );
    // Remove all p tags
    html = html.replace(/<\/?p[^>]*>/gi, "" );
    // Remove all em tags
    html = html.replace(/<\/?em[^>]*>/gi, "" );
    // Remove all u tags
    html = html.replace(/<\/?[uU][^>]*>/gi, "" );
    // Remove all I tags
    html = html.replace(/<\/?I[^>]*>/gi, "" );
    // Remove all SPAN tags
    html = html.replace(/<\/?SPAN[^>]*>/gi, "" );
    // Remove all STRIKE tags
    html = html.replace(/STRIKE/gi, "strike" );
    html = html.replace(/<\/?strike[^>]*>/gi, "" );
    // Remove all strong tags
    html = html.replace(/<\/?strong[^>]*>/gi, "" );
    // Remove all TBODY tags
    html = html.replace(/TBODY/gi, "tbody" );
    html = html.replace(/<\/?tbody[^>]*>/gi, "" );
    // Remove all div tags
    html = html.replace(/<\/?div[^>]*>/gi, "" );
    //Remove font
    html = html.replace( /<(font[^>]*)>/gi, "" ) ;
    html = html.replace( /<\/font>/gi, "" ) ;
    //html = html.replace(/<\/?A[^>]*>/gi, "" );
    //Remove H
    html = html.replace( /<H1>/gi, "" ) ;
    html = html.replace( /<\/H1>/gi, "" ) ;
    html = html.replace( /<H2>/gi, "" ) ;
    html = html.replace( /<\/H2>/gi, "" ) ;
    html = html.replace( /<H3>/gi, "" ) ;
    html = html.replace( /<\/H3>/gi, "" ) ; 
    // Replace the blanks to only one blank;
    //html = html.replace(/(\S)+/gi, " " );
    // Remove "display:none" tags.  
    //html = html.replace( /<(\w+)[^>]*\sstyle="[^"]*DISPLAY\s?:\s?none(.*?)<\/\1>/ig, '' ) ;
    //keep colspan or rowspan
    //html = html.replace(/<td[^>]*(colspan="\d+"|colspan=\d)[^>]*>/gi, "<td $1>" );
    //html = html.replace(/<([a-zA-Z]{2,5})[^>]*colspan=("\d+"|\d+)[^>]*/gi,"<$1 colspan=$2"); 
    //html = html.replace(/<([a-zA-Z]{2,5})[^>]*rowspan=("\d+"|\d+)[^>]*/gi,"<$1 rowspan=$2"); 
    // Remove all COLGROUP tags
    html = html.replace(/<\/?COLGROUP[^>]*>/gi, "" );
    html = html.replace(/<\/?COL[^>]*>/gi, "" );
    // cut all table tags
    html = html.replace(/TABLE/gi, "table" );
    html = html.replace(/<table[^>]*>/gi, "<table>" );
    // turn all td tags
    html = html.replace(/TD/gi, "td" );
    //html = html.replace(/<td[^>]*>/gi, "<td>" );
    // turn all tr tags
    html = html.replace(/TR/gi, "tr" );
    //html = html.replace(/<tr[^>]*>/gi, "<tr>" );
    // Remove Class attributes
    html = html.replace(/<(\w[^>]*) class=([^ |>]*)([^>]*)/gi, "<$1$3") ;
    // Remove Style attributes
    html = html.replace(/<(\w[^>]*) style="([^"]*)"([^>]*)/gi, "<$1$3") ;
    // Remove Lang attributes
    html = html.replace(/<(\w[^>]*) lang=([^ |>]*)([^>]*)/gi, "<$1$3") ;
    // Remove width attributes
    html = html.replace(/<(\w[^>]*) width=([^ |>]*)([^>]*)/gi, "<$1$3") ;
    // Remove align attributes
    html = html.replace(/<(\w[^>]*) align=([^ |>]*)([^>]*)/gi, "<$1$3") ;
    // Remove Valign attributes
    html = html.replace(/<(\w[^>]*) Valign=([^ |>]*)([^>]*)/gi, "<$1$3") ;
    // Remove height attributes
    html = html.replace(/<(\w[^>]*) height=([^ |>]*)([^>]*)/gi, "<$1$3") ;
    // Remove XML elements and declarations
    html = html.replace(/<\\?\?xml[^>]*>/gi, "") ;
    // Remove Tags with XML namespace declarations: <o:p></o:p>
    html = html.replace(/<\/?\w+:[^>]*>/gi, "") ;
    // Remove bgcolor attributes
    html = html.replace(/<(\w[^>]*) bgcolor=([^ |>]*)([^>]*)/gi, "<$1$3") ;
    // Remove x:str attributes
    html = html.replace(/<(\w[^>]*) x:str=([^ |>]*)([^>]*)/gi, "<$1$3") ;
    html = html.replace(/<td ">/gi, "<td>" );
    // Remove blank lines
    html = html.replace(/\n\s*\r/gi, "" );
    // Remove blanks before or after a line
    html = html.replace(/^\s*|\s*$/gi, "" );
    // Remove &nbsp;
    html = html.replace(/&nbsp;/gi, "" );
    // Remove empty tags (three times, just to be sure).Also removes any empty anchor  
    html = html.replace( /<([^\s>]+)(\s[^>]*)?>\s*<\/\1>/g, '' ) ;  
    html = html.replace( /<([^\s>]+)(\s[^>]*)?>\s*<\/\1>/g, '' ) ;  
    html = html.replace( /<([^\s>]+)(\s[^>]*)?>\s*<\/\1>/g, '' ) ;
    // Remove blanks
    //html = html.replace(/\s/gi, "" );
    // Remove x:num attributes
    html = html.replace(/<(\w[^>]*) x:num=([^ |>]*)([^>]*)/gi, "<$1$3") ;
    //把td里面的p去除
    //var re = new RegExp("(<td>[^>]*<P)([^>]*>.*?)(<\/P><\/td>)","gi") ;
    //Different because of a IE 5.0 error
    //html = html.replace( re, "<td$2</td>" ) ;
    //删除p空格
    //html = html.replace(/<(p[^>]*)><\/p>/gi, "");
    //html = html.replace(/<(p[^>]*)>&nbsp;<\/p>/gi, "");
    
    return html;
}


