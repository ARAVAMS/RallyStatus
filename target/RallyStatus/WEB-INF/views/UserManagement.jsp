<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    
 <style type="text/css">
  .handpointer {

    cursor:pointer;
}
.responsive-table {
  color: #000;
  overflow: hidden;
  width:100%;
  border-radius: 5px;
  background-color:#eaebee;
}
.responsive-table tr {
      border-bottom: 1px solid #dcdfe5;
}
.responsive-table th,.responsive-table td {
    padding-left: 15px !important;
}

.responsive-table tr:last-child {
    border-bottom: 0px;
   
}
a:focus {
  -moz-outline-style: none;
}

a, a:active, a:focus {
outline: none;
}

.bgOrg {
    background-color:#fa902b;
    color:#fff;
}
  
  .state-table td div, .state-block div {
    background: #fff;
    border: 1px solid #ddd;
    color: #ddd;
    padding-left: 1px;
    height: 16px;
    line-height: 14px;
    text-align: center;
    width: 13px;
}  
  </style>
  </head>
<body  ng-app="myApp" class="ng-cloak">
  
    
    <!--PlaceHolder for Views-->
    <div ng-controller="UserController as ctrl">
        <div class="container">
            <div class="row panel-heading bgOrg">
                <div class="col-sm-1 col-xs-12 pull-left">#</div>
                <div class="col-sm-2 col-xs-12">U.S.ID</div>
                <div class="col-sm-3 col-xs-12">U.S.Name</div>
                <div class="col-sm-2 col-xs-12">Status</div>
               <!-- <div class="col-sm-2 col-xs-12">Order Date</div>
                <div class="col-sm-2 col-xs-12">#Avail</div>-->
            </div>

            <div ng-repeat="u in ctrl.users">
                <div class="row panel panel-body">
                    <div class="col-xs-1">
                        <div class="handpointer glyphicon glyphicon-chevron-right" data-ng-click="collapse($event)" data-target="#view_{{u.id}}" data-toggle="collapse" 
                             aria-expanded="false" 
                             data-ng-if="u.task!=null">
                        </div>
                    </div>
                    <div class="col-sm-2 col-xs-12" data-ng-bind="u.id"></div>
                    <div class="col-sm-3 col-xs-12" data-ng-bind="u.username"></div>
                    <div class="col-sm-2 col-xs-12">
                    <td><table class="state-table" style="margin-right:17px;"><tbody><tr>
    
        <td class="selected">
            <div title="Defined">
                D
            </div>
        </td>
    
        <td>
            <div title="In-Progress">
                P
            </div>
        </td>
    
        <td>
            <div title="Completed">
                C
            </div>
        </td>
    
        <td>
            <div title="Accepted">
                A
            </div>
        </td>
    
</tr></tbody></table></td>  
                    </div>
                  </div>

                <div class="collapse" id="view_{{u.id}}" data-ng-if="u.task!=null">
                    <div class="col-sm-offset-1">
                        <table class="table-condensed responsive-table">
                            <tr class="row">
                                <th>Task.ID</th>
                                <th> Task Name</th>
                                <th>Status</th>
                                
                            </tr>
                            <tr class="row" ng-repeat="item in u.task">
                                <td data-ng-bind="item.taskPriority"></td>
                                <td data-ng-bind="item.taskName"></td>
                                 <td><table class="state-table" style="margin-right:17px;"><tbody><tr>
    
        <td class="selected">
            <div title="Defined">
                D
            </div>
        </td>
    
        <td>
            <div title="In-Progress">
                P
            </div>
        </td>
    
        <td>
            <div title="Completed">
                C
            </div>
        </td>
    
        <td>
            <div title="Accepted">
                A
            </div>
        </td>
    
</tr></tbody></table></td>  
                              </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.js"></script>
    <script src="https://code.angularjs.org/1.3.11/angular-route.min.js"></script>
   <!--  <script type="text/javascript" src="homeController.js"></script> -->
  <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/user_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/user_controller.js' />"></script>
</body>
</html>
