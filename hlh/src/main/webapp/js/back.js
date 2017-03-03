/**
 * Created by Administrator on 2016/10/8.
 */
//∑µªÿ…œ“ª“≥

function back(){
	window.history.back();
}

function return_prepage()
{
    if(window.document.referrer=="" || window.document.referrer==window.location.href)
    {
        window.location.href="{dede:type}[field:typelink /]{/dede:type}";
    }else
    {
        window.location.href=window.document.referrer;
    }

}