function show_intro(){
    jQuery("#start_menu").trigger('click');
    document.getElementById("show_intro_back").style.display="block";
    document.getElementById("show_intro_content").style.display="block";
}
function hide_intro_guide(){
    document.getElementById("show_intro_back").style.display="none";
    document.getElementById("show_intro_content").style.display="none";
}
function hide_intro_guide_forever(){
    document.getElementById("show_intro_back").style.display="none";
    document.getElementById("show_intro_content").style.display="none";
    
    //--TODO 改为本地方法
    jQuery.ajax({
        type: 'get',
        url:'',
        data: "change_val=3"
    });
}

function show_guide(){
    document.getElementById("show_guide_content").style.display="block";
    document.getElementById("show_guide_back").style.display="block";
}

function hide_show_guide(){
    document.getElementById("show_guide_content").style.display="none";
    document.getElementById("show_guide_back").style.display="none";
}