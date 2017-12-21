<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/cj/title_setting.jsp" %>
<div id="sidebar" class="sidebar responsive">
    <script type="text/javascript">
        try {
            ace.settings.check('sidebar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <button class="btn btn-success" style="height: 32px;">
                <i class="ace-icon fa fa-signal"></i>
            </button>
            <button class="btn btn-info" style="height: 32px;">
                <i class="ace-icon fa fa-pencil"></i>
            </button>

            <button class="btn btn-warning" style="height: 32px;">
                <i class="ace-icon fa fa-users"></i>
            </button>

            <button class="btn btn-danger" style="height: 32px;">
                <i class="ace-icon fa fa-cogs"></i>
            </button>
        </div>
        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
        </div>
    </div><!-- /.sidebar-shortcuts -->

    <ul class="nav nav-list">
        <li id="dataCheck" class="">
            <a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-desktop"></i>
                <span class="menu-text">
						数据质量检查
					</span>

                <b class="arrow fa fa-angle-down"></b>
            </a>

            <b class="arrow"></b>

            <ul class="submenu">
                <li id="checkIndex" class="">
                    <a href="${ctx }/wdac/dataCheck/toCheckIndex">
                        <i class="menu-icon fa fa-caret-right"></i>
                        问题数据检测
                    </a>
                    <b class="arrow"></b>
                </li>

                <li id="searchIndex" class="">
                    <a href="${ctx }/wdac/dataCheck/toSearchIndex">
                        <i class="menu-icon fa fa-caret-right"></i>
                        问题数据查询
                    </a>

                    <b class="arrow"></b>
                </li>

                <li id="tjIndex" class="">
                    <a href="${ctx }/wdac/dataCheck/toTjIndex">
                        <i class="menu-icon fa fa-caret-right"></i>
                        问题数据统计
                    </a>

                    <b class="arrow"></b>
                </li>

                <li id="fkIndex" class="">
                    <a href="${ctx }/wdac/dataCheck/toFkIndex">
                        <i class="menu-icon fa fa-caret-right"></i>
                        问题数据反馈
                    </a>

                    <b class="arrow"></b>
                </li>

                <li id="logIndex" class="">
                    <a href="${ctx }/wdac/dataCheck/toLog">
                        <i class="menu-icon fa fa-caret-right"></i>
                        问题数据日志管理
                    </a>

                    <b class="arrow"></b>
                </li>


            </ul>
        </li>

        <li class="">
            <a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-list"></i>
                <span class="menu-text"> 数据标签管理 </span>

                <b class="arrow fa fa-angle-down"></b>
            </a>

            <b class="arrow"></b>

            <ul class="submenu">
                <li class="">
                    <a href="bqjsywh.html">
                        <i class="menu-icon fa fa-caret-right"></i>
                        数据标签检索与维护
                    </a>

                    <b class="arrow"></b>
                </li>

                <li class="">
                    <a href="bqzltj.html">
                        <i class="menu-icon fa fa-caret-right"></i>
                        数据标签总量统计
                    </a>

                    <b class="arrow"></b>
                </li>
                <li class="">
                    <a href="bqdysjltj.html">
                        <i class="menu-icon fa fa-caret-right"></i>
                        数据标签对应数据量统计
                    </a>

                    <b class="arrow"></b>
                </li>
                <li class="">
                    <a href="bqrdtj.html">
                        <i class="menu-icon fa fa-caret-right"></i>
                        数据标签热度统计
                    </a>

                    <b class="arrow"></b>
                </li>
            </ul>
        </li>

        <li id="dataDuibi" class="">
            <a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-pencil-square-o"></i>
                <span class="menu-text"> 数据增量比对 </span>

                <b class="arrow fa fa-angle-down"></b>
            </a>

            <b class="arrow"></b>

            <ul class="submenu">
                <li id="duibiIndex" class="">
                    <a href="${ctx }/wdac/dataDuibi/toDuibiIndex">
                        <i class="menu-icon fa fa-caret-right"></i>
                        比对方案设置
                    </a>

                    <b class="arrow"></b>
                </li>

                <li id="queryIndex" class="">
                    <a href="${ctx }/wdac/dataDuibi/toDuibibdjgcx">
                        <i class="menu-icon fa fa-caret-right"></i>
                        比对结果查询
                    </a>

                    <b class="arrow"></b>
                </li>

                <li id="countIndex" class="">
                    <a href="${ctx }/wdac/dataDuibi/toDuibibdjgtj">
                        <i class="menu-icon fa fa-caret-right"></i>
                        比对结果统计
                    </a>

                    <b class="arrow"></b>
                </li>
                <li id="rzIndex" class="">
                    <a href="${ctx }/wdac/dataDuibi/toLog">
                        <i class="menu-icon fa fa-caret-right"></i>
                        比对日志管理
                    </a>

                    <b class="arrow"></b>
                </li>

            </ul>
        </li>
    </ul><!-- /.nav-list -->

    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
        <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left"
           data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>

    <script type="text/javascript">
        try {
            ace.settings.check('sidebar', 'collapsed')
        } catch (e) {
        }
    </script>
</div>
			