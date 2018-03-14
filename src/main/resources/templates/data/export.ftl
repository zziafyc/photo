<@override name="title">用户列表</@override>

<@override name="header">
    <form class="form-inline">
        <div class="form-group">
            <label for="nickname">名称</label>
            <input type="text" class="form-control" id="name"
                   placeholder="搜索名称">
        </div>
        <button type="submit" class="btn btn-default" onclick="return search()">搜索
        </button>
    </form>
</@override>


<@override name="table">
    <table id="userTable" class="table table-striped table-bordered table-hover"
           cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>id</th>
            <th>名称</th>
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
                url: "storage/data",
                dataSrc: "data"
            },
            columns: [
                {data: 'project_id', defaultContent: '', "searchable": true},
                {data: 'projectName', defaultContent: '', "searchable": true},
                {
                    data: 'id', render: function (data) {
                        if (data === undefined) {
                            return '';
                        }
                        return "<button class='btn btn-default' onclick='return exportStationInfo(\"" + data + "\")'>导出</button>";
                    }, "searchable": true
                }
            ],
            "language": {
                url: "js/vendor/DataTables/Chinese.json"
            }
        });
    });

    function exportStationInfo(code) {
        window.open("storage/export?id=" + code);
        return false;
    }
    function search() {
        table.ajax.url("storage/data?name=" +
                $("#name").val()).draw();
        return false;
    }
</script>
</@override>
<@extends name="../base/baseTable.ftl"/>