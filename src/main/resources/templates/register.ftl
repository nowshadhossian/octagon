
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>SB Admin - Start Bootstrap Template</title>
    <!-- Bootstrap core CSS-->
    <link href="/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="/css/font-awesome/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Custom styles for this template-->
    <link href="/css/sb-admin.css" rel="stylesheet">
</head>

<body class="bg-dark">
<div class="container">
    <div class="card card-register mx-auto mt-5">
        <div class="card-header">Register an Account</div>
        <div class="card-body">
            <form>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <label for="firstName">First name</label>
                            <input class="form-control" id="firstName" name="firstName" type="text" aria-describedby="nameHelp" placeholder="Enter first name">
                        </div>
                        <div class="col-md-6">
                            <label for="lastName">Last name</label>
                            <input class="form-control" id="lastName" name="lastName" type="text" aria-describedby="nameHelp" placeholder="Enter last name">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label>Date Of Birth</label>
                    <input type="text" id="datepicker" name="dateOfBirth" class="form-control" placeholder="Choose">
                </div>

                <div class="form-group">
                    <label for="phoneNo">Phone No</label>
                    <input class="form-control" id="phoneNo" name="phoneNo" type="number" aria-describedby="phoneNo" placeholder="Enter Phone Number">
                </div>

                <div class="form-group">
                    <label for="comment">Address:</label>
                    <textarea class="form-control" rows="5" id="address" name="address"></textarea>
                </div>

                <div class="form-group">
                    <label class="form-check-label" for="defaultCheck2">Enrolling..</label>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                        <label class="form-check-label" for="defaultCheck1">
                            Physics
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="defaultCheck2">
                        <label class="form-check-label" for="defaultCheck2">
                            Chamistry
                        </label>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="defaultCheck3">
                        <label class="form-check-label" for="defaultCheck3">
                            Biology
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <label for="exampleFormControlSelect1">Session</label>
                    <select class="form-control" id="session">
                        <option>Summer 2018</option>
                        <option>Spring 2018</option>
                    </select>
                </div>

                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                                <label for="exampleFormControlSelect1">Subject</label>
                                <select class="form-control" id="subject">
                                    <option>Physics</option>
                                    <option>Chemistry</option>
                                    <option>Bio</option>
                                </select>
                        </div>
                        <div class="col-md-6">
                            <label for="lastName">Last name</label>
                            <input class="form-control" id="refereesName" name="refereesName" type="text" aria-describedby="nameHelp" placeholder="Enter referees name">
                        </div>
                    </div>
                </div>

                <div class="form-group guardian-col">
                    <div class="guardian-rows">
                        <div class="form-row guardian-row">
                            <div class="col-md-3">
                                <label for="guardianName">Name</label>
                                <input class="form-control" id="guardianName-0" name="guardianName" type="text"
                                       aria-describedby="nameHelp" placeholder="Enter name">
                            </div>
                            <div class="col-md-3">
                                <label for="contactNo">Contact No</label>
                                <input class="form-control" id="contactNo-0" name="contactNo" type="text"
                                       aria-describedby="nameHelp" placeholder="Enter No">
                            </div>

                            <div class="col-md-3">
                                <label for="relation">Relation</label>
                                <input class="form-control" id="relation-0" name="relation" type="text"
                                       aria-describedby="nameHelp" placeholder="Enter Relation">
                            </div>

                            <div class="col-md-3">
                                <label for="email">Email</label>
                                <input class="form-control" id="email-0" name="email" type="text"
                                       aria-describedby="nameHelp"
                                       placeholder="Enter Email">
                            </div>
                        </div>
                    </div>
                    <a class="d-block small mt-3 add-guardian-link">Add Guardian</a>
                    <a class="d-block small mt-3 remove-guardian-link">Remove Guardian</a>
                </div>

                <script id="template-guardian-list-item" type="text/template">
                    <div class="form-row guardian-row">
                        <div class="col-md-3">
                            <input class="form-control" name="guardianName" type="text" aria-describedby="nameHelp" placeholder="Enter name">
                        </div>
                        <div class="col-md-3">
                            <input class="form-control" name="contactNo" type="text" aria-describedby="nameHelp" placeholder="Enter No">
                        </div>

                        <div class="col-md-3">
                            <input class="form-control" name="relation" type="text" aria-describedby="nameHelp" placeholder="Enter Relation">
                        </div>

                        <div class="col-md-3">
                            <input class="form-control" name="email" type="text" aria-describedby="nameHelp" placeholder="Enter Email">
                        </div>
                    </div>
                </script>

                <div class="form-group">
                    <label for="email">Email address</label>
                    <input class="form-control" id="email" name="email" type="email" aria-describedby="emailHelp" placeholder="Enter email">
                </div>

                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <label for="password">Password</label>
                            <input class="form-control" id="password" name="password" type="password" placeholder="Password">
                        </div>
                        <div class="col-md-6">
                            <label for="confirmPassword">Confirm password</label>
                            <input class="form-control" id="confirmPassword" name="confirmPassword" type="password" placeholder="Confirm password">
                        </div>
                    </div>
                </div>
                <a class="btn btn-primary btn-block" href="/register">Register</a>
            </form>
            <div class="text-center">
                <a class="d-block small mt-3" href="/login">Login Page</a>
                <a class="d-block small" href="/forgot-password">Forgot Password?</a>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript-->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="/js/jquery-easing/jquery.easing.min.js"></script>
<script src="/js/sb-admin.min.js"></script>
</body>
</html>