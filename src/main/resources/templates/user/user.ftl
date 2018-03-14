<@override name="title">用户列表</@override>

<@override name="header">

<form class="form-inline">
</form>

</@override>


<@override name="table">
    <table id="userTable" class="table table-striped table-bordered table-hover"
           cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>名称</th>
            <th>申请时间</th>
            <th>备注</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</@override>

<@override name="script">
<script>
    var table;
    $(document).ready(function () {
        table = $('#userTable').DataTable({
            "searching": false,
            'serverSide': true,
            "ajax": {
                url: "user/user",
                dataSrc: "data"
            },
            columns: [{data: 'id', defaultContent: '', "searchable": true},
                {data: 'createTime', defaultContent: '', "searchable": true},
                {data: 'remark', defaultContent: '', "searchable": true},
                {
                    data: 'status', render: function (data) {
                        if (data === undefined) {
                            return '';
                        }

                        if (data === 0) {
                            return "<span class='btn btn-danger'>未通过</span>";
                        } else {
                            return "<span class='btn btn-success'>已通过</span>";
                        }
                    }, "searchable": true
                },
                {
                    data: 'id', render: function (data) {
                        if (data === undefined) {
                            return '';
                        }
                        var updateButton = "<a class='btn btn-success J_ajax_content_modal' data-href='userUpdate?id=" + data + "'>更新</a>";
                        var deleteButton = "<a class='btn btn-danger J_ajax_content_modal' data-href='userDelete?id=" + data + "'>删除</a>";
                        return updateButton + deleteButton;
                    }, "searchable": true
                }
            ],
            "language": {
                url: "js/vendor/DataTables/Chinese.json"
            }
        });
    });
</script>
</@override>
<@extends name="../base/baseTable.ftl"/>