<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">

<head>
    <#include "/layout/common/header.ftl">
</head>

<body class="bg-white">
<div class="container">
    <#if successMsg??>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            ${successMsg}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </#if>
    <#if errorMsg??>
        <div class="alert alert-warning alert-dismissible fade show" role="alert">
            ${errorMsg}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </#if>
