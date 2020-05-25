<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href="images/favicon.ico" type="image/ico" />

        <title>QRFilesTransfer</title>

        <!-- Bootstrap -->
        <link href="vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">

        <!-- iCheck -->
        <link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">
        <!-- Custom Theme Style -->
        <link href="build/css/custom.min.css" rel="stylesheet">
    </head>

    <body class="nav-md">
        <div class="container body">
            <div class="main_container">
                <div class="col-md-3 left_col">
                    <div class="left_col scroll-view">
                        <div class="navbar nav_title" style="border: 0;">
                            <a href="index" class="site_title"><i class="fa fa-paw"></i> <span>QRFileTransfer</span></a>
                        </div>

                        <div class="clearfix"></div>
                        <!-- sidebar menu -->
                        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                            <div class="menu_section">
                                <ul class="nav side-menu">
                                    <li><a href="/"><i class="fa fa-home"></i> FileDownload <span
                                            class="fa fa-chevron-down"></span></a>
                                    </li>
                                    <li><a href="fileUpload"><i class="fa fa-edit"></i> FileUpload <span
                                            class="fa fa-chevron-down"></span></a>
                                    </li>
                                </ul>
                            </div>

                        </div>
                    </div>
                </div>
                <!-- page content -->
                <div class="right_col" role="main">
                    <div class="clearfix"></div>
                    <div class="row">
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="x_panel">

                                <div class="x_content">

                                    <form id="fileUploadForm" class="form-horizontal form-label-left" enctype="multipart/form-data">

                                        <div class="form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="file-name">File
                                            Upload
                                            <span class="required">*</span>
                                        </label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input type="file" id="fileupload" name="fileupload" class="form-control col-md-7 col-xs-12">
                                            </div>
                                        </div>

                                        <div class="ln_solid"></div>
                                        <div class="form-group">
                                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                                <button class="btn btn-success" type="button" onclick="doUpload()">Upload</button>
                                                <button class="btn btn-primary" type="reset">Reset</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Or Scan the QRCode on the phone</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <img id="UploadQR" src="/upload/getUploadQRCode" />
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /page content -->

                <!-- footer content -->
                <footer>
                    <div class="pull-right">
                        Power by lovelyshaking
                    </div>
                    <div class="clearfix"></div>
                </footer>
                <!-- /footer content -->
            </div>
        </div>

        <!-- jQuery -->
        <script src="../vendors/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="../vendors/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- FastClick -->
        <script src="../vendors/fastclick/lib/fastclick.js"></script>

        <!-- iCheck -->
        <script src="../vendors/iCheck/icheck.min.js"></script>
        <!-- Custom Theme Scripts -->
        <script src="../build/js/custom.js"></script>
        <script type="text/javascript">
            function doUpload() {
                var formData = new FormData($("#fileUploadForm")[0]);
                $.ajax({
                    url: "/upload/uploadFile",
                    type: 'POST',
                    data: formData,
                    async: false,
                    cache: false,
                    contentType: false,
                    processData: false,
                    success: function(data) {
                        console.log(data)
                        alert(data);
                    },
                    error: function(data) {
                        alert("failed!");
                    }
                });
            }
        </script>
    </body>

    </html>