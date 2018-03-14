<#assign contextPath = '#'/>
<#setting number_format="#">
<#assign context=ctx.contextPath/>
<#assign didiImg = '${context}/images/didi.png'/>

<link rel="stylesheet" href="${context}/js/vendor/DataTables/css/dataTables.bootstrap.css">
<script src="${context}/js/vendor/jquery.min.js"></script>
<script src="${context}/js/vendor/DataTables/js/jquery.dataTables.min.js"></script>
<script src="${context}/js/vendor/DataTables/js/dataTables.bootstrap.min.js"></script>
<script src="${context}/js/refuel.js"></script>

<div class="content_wrapper">

    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-12">
                <aside>
                    <header><i class="fa fa-fw fa-file"></i><@block name="title">标题</@block></header>
                    <section>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="btn-group" style="margin-bottom: 20px">
                                        <@block name="header">

                                        </@block>
                                    </div>
                                    <div class="table-responsive">
                                        <@block name="table">

                                        </@block>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </section>
                </aside>
            </div>
        </div>
    </div>
</div>
<@block name="script">

</@block>
