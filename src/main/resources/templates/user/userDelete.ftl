<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
            class="sr-only">Close</span></button>
    <h4 class="modal-title" id="myModalLabel">删除用户</h4>
</div>
<form class="form-horizontal J_ajaxForm" role="form" action="">
    <div class="modal-body" id="main-body">
        <div class="form-group">
            <label class="col-sm-3 control-label">id</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="id" value="${user.id!}" readonly
                       name="id">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">备注</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" readonly placeholder="商品分类名称" value="${user.remark!}"
                       name="remark">
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-danger" onclick="return deleteUser('${user.id!}')">
            删除用户
        </button>
    </div>
</form>

<script>
    function deleteUser(id) {
        var formBody = $("#main-body");
        var data = {
            id: formBody.find("input[name='id']").val()
        };
        $.ajax({
            url: "user/delete",
            method: "POST",
            data: data,
            success: function (res) {
                if (res.status === 0) {
                    location.reload();
                } else {
                    alert("删除失败:" + res.msg)
                }
            }
        });
        return false;
    }
</script>