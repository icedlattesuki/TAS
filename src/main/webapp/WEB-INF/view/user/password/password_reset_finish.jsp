<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld"%>
<tmpl:overwrite name="body">
    <div class="row">
        <div class="col s0 m4 l4"></div>
        <div class="col s0 m4 l4">
            <div class="section"></div>
            <div class="row">
                <div class="col s12">
                    <div class="card teal lighten-2">
                        <div class="card-content white-text">
                            <span class="card-title" align="center">${info}</span>
                        </div>
                        <div class="card-action">
                            <a href="/login" class="white-text lighten-3" align="center">重新登录<i class="material-icons right">arrow_forward</i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col s0 m4 l4"></div>
    </div>
</tmpl:overwrite>
<jsp:include page="../../template/base.jsp" />