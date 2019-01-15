$("#submitBtn").click(
    function () {
        submitblog();
    }
)
function submitblog() {
    var  title = $("#title").val();
    var content = $("#content").val();
    var html = $("#htmlContent").val();
    console.log(title);
    $.ajax({
        url: "submit",
        method: 'POST',
        data: {title: title, content:content,html:html},
        success:function () {
            console.log("success")
        },
        error:function () {
            console.log("failed")
        }
    })
}