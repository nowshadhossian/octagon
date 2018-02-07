
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
                        <input class="form-check-input" type="checkbox" value="" id="defaultCheck2">
                        <label class="form-check-label" for="defaultCheck2">
                            Biology
                        </label>
                    </div>
                </div>

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
                <a class="btn btn-primary btn-block" href="login.html">Register</a>
            </form>
            <div class="text-center">
                <a class="d-block small mt-3" href="/login">Login Page</a>
                <a class="d-block small" href="/forgot-password">Forgot Password?</a>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript-->
<script src="/jq/jquery.min.js"></script>
<script src="/jq/bootstrap/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="/jq/jquery-easing/jquery.easing.min.js"></script>
</body>
</html>