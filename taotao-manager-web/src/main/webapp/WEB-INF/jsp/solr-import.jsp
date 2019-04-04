<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">

    <tr>
        <td>
            <input type="submit" value="导入商品索引" class="easyui-linkbutton selectItemCat" onclick="solrImport()" >
        </td>
    </tr>

</div>
<script type="text/javascript">
    function solrImport() {
        $.post("/solr/import", function (data) {
            if (data.status == 200) {
                $.messager.alert('提示', '导入成功');
            }else
                $.messager.alert('提示', '导入失败');
        });
    }
</script>
