function splitter(resp) {
    var splits = resp.split('=');
    if (splits != null && splits.length > 1 && splits[1] != null) {
        return splits[1];
    }
    return 0;
}
$(document).ready(function() {
    $('.selectpicker').selectpicker();


    var fixHelperModified = function(e, tr) {
            var $originals = tr.children();
            var $helper = tr.clone();
            $helper.children().each(function(index) {
                $(this).width($originals.eq(index).width())
            });
            return $helper;
        },
        updateIndex = function(e, ui) {
            $('td.index', ui.item.parent()).each(function(i) {
                $(this).html(i + 1);
            });
            $('input[type=text]', ui.item.parent()).each(function(i) {
                $(this).val(i + 1);
            });
        };


    $("#add_row").click(function() {
        document.getElementById("tableOfRules").style.display = "table";
        var selectedTechnology = $('#technologySelector').val();
        var selectedYears = $('#yearSelector').val();
        var selectedExperience = $('#experienceSelector').val();
        var selectedYearsHelper = $('#yearSelectorHelper').val();
        var selectedExperienceHelper = $('#experienceSelectorHelper').val();
        var selectedScore = $('#score').val();

        $.ajax({
            type: "POST",
            url: "/eJobFinder/rule/technology",
            data: jQuery.param({
                name: selectedTechnology,
                level: selectedExperience,
                year: selectedYears,
                yearOperator: selectedYearsHelper,
                levelOperator: selectedExperienceHelper,
                score: selectedScore
            }),
            timeout: 600000,
            success: function(data) {
                var newRow = document.createElement('tr');
                var number = splitter(data);
                var rowID = "row" + "_tech_" + number;
                newRow.setAttribute("id", rowID);
                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + "Candidate should know " + selectedTechnology + " at level " + selectedExperienceHelper + " " + selectedExperience + " with years of experience " + selectedYearsHelper + " " + selectedYears + '</td>' +
                    ' <td>' + selectedTechnology + '</td>' +
                    ' <td>' + selectedExperience + '</td>' +
                    ' <td>' + selectedYears + '</td>' +
                    ' <td>' + selectedScore + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';

                $("#tableOfRules tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });

    $("#skill_add_row").click(function() {
        document.getElementById("skill_tableOfRules").style.display = "table";
        var selectedName = $('#skillSelector').val();
        var selectedExperience = $('#skill_experienceSelector').val();
        var selectedExperienceHelper = $('#skill_experienceSelectorHelper').val();
        var selectedScore = $('#skill_score').val();

        $.ajax({
            type: "POST",
            url: "/eJobFinder/rule/skill",
            data: jQuery.param({
                name: selectedName,
                level: selectedExperience,
                levelOperator: selectedExperienceHelper,
                score: selectedScore
            }),
            timeout: 600000,
            success: function(data) {
                var number = splitter(data);
                var newRow = document.createElement('tr');
                var rowID = "row" + "_skill_" + number;
                newRow.setAttribute("id", rowID);
                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + "Candidate should have " + selectedName + " at level " + selectedExperienceHelper + " " + selectedExperience + '</td>' +
                    ' <td>' + selectedName + '</td>' +
                    ' <td>' + selectedExperience + '</td>' +
                    ' <td>' + selectedScore + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';
                $("#skill_tableOfRules tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });

    $("#tool_add_row").click(function() {
        document.getElementById("tool_tableOfRules").style.display = "table";
        var selectedName = $('#tool_nameSelector').val();
        var selectedYears = $('#tool_yearSelector').val();
        var selectedYearsHelper = $('#tool_yearSelectorHelper').val();
        var selectedExperience = $('#tool_experienceSelector').val();
        var selectedExperienceHelper = $('#tool_experienceSelectorHelper').val();
        var selectedScore = $('#tool_score').val();


        $.ajax({
            type: "POST",
            url: "/eJobFinder/rule/tool",
            data: jQuery.param({
                name: selectedName,
                level: selectedExperience,
                year: selectedYears,
                yearOperator: selectedYearsHelper,
                levelOperator: selectedExperienceHelper,
                score: selectedScore
            }),
            timeout: 600000,
            success: function(data) {
                var number = splitter(data);
                var newRow = document.createElement('tr');
                var rowID = "row" + "_tool_" + number;
                newRow.setAttribute("id", rowID);
                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + "Candidate should know " + selectedName + " at level " + selectedExperienceHelper + " " + selectedExperience + " with years of experience " + selectedYearsHelper + " " + selectedYears + '</td>' +
                    ' <td>' + selectedName + '</td>' +
                    ' <td>' + selectedExperience + '</td>' +
                    ' <td>' + selectedYears + '</td>' +
                    ' <td>' + selectedScore + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';
                $("#tool_tableOfRules tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });


    $("#lang_add_row").click(function() {
        document.getElementById("lang_tableOfRules").style.display = "table";
        var selectedName = $('#langSelector').val();
        var selectedExperience = $('#lang_experienceSelector').val();
        var selectedExperienceHelper = $('#lang_experienceSelectorHelper').val();
        var selectedScore = $('#lang_score').val();


        $.ajax({
            type: "POST",
            url: "/eJobFinder/rule/language",
            data: jQuery.param({
                name: selectedName,
                level: selectedExperience,
                levelOperator: selectedExperienceHelper,
                score: selectedScore
            }),
            timeout: 600000,
            success: function(data) {
                var number = splitter(data);
                var newRow = document.createElement('tr');
                var rowID = "row" + "_lang_" + number;
                newRow.setAttribute("id", rowID);

                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + "Candidate should know " + selectedName + " at level " + selectedExperienceHelper + " " + selectedExperience + '</td>' +
                    ' <td>' + selectedName + '</td>' +
                    ' <td>' + selectedExperience + '</td>' +
                    ' <td>' + selectedScore + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';

                $("#lang_tableOfRules tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });

    $("#loc_add_row").click(function() {
        document.getElementById("loc_tableOfRules").style.display = "table";
        var selectedName = $('#locSelector').val();
        var selectedScore = $('#loc_score').val();

        $.ajax({
            type: "POST",
            url: "/eJobFinder/rule/location",
            data: jQuery.param({
                name: selectedName,
                score: selectedScore
            }),
            timeout: 600000,
            success: function(data) {
                var number = splitter(data);
                var newRow = document.createElement('tr');
                var rowID = "row" + "_loc_" + number;
                newRow.setAttribute("id", rowID);
                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + selectedName + '</td>' +
                    ' <td>' + selectedScore + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';


                $("#loc_tableOfRules tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });

    $("#workH_add_row").click(function() {
        document.getElementById("workH_tableOfRules").style.display = "table";
        var selectedName = $('#workHSelector').val();
        var selectedHelper = $('#workH_SelectorHelper').val();
        var selectedScore = $('#workH_score').val();

        $.ajax({
            type: "POST",
            url: "/eJobFinder/rule/workingHours",
            data: jQuery.param({
                name: selectedName,
                operator: selectedHelper,
                score: selectedScore
            }),
            timeout: 600000,
            success: function(data) {
                var number = splitter(data);
                var newRow = document.createElement('tr');
                var rowID = "row" + "_workH_" + number;
                newRow.setAttribute("id", rowID);

                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + "Woking hours are " + selectedName + '</td>' +
                    ' <td>' + selectedName + '</td>' +
                    ' <td>' + selectedScore + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';

                $("#workH_tableOfRules tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });
    $("#contr_add_row").click(function() {
        document.getElementById("contr_tableOfRules").style.display = "table";
        var selectedName = $('#contrSelector').val();
        var selectedHelper = $('#contr_SelectorHelper').val();
        var selectedScore = $('#contr_score').val();


        $.ajax({
            type: "POST",
            url: "/eJobFinder/rule/typeOfContract",
            data: jQuery.param({
                name: selectedName,
                operator: selectedHelper,
                score: selectedScore
            }),
            timeout: 600000,
            success: function(data) {
                var number = splitter(data);
                var newRow = document.createElement('tr');
                var rowID = "row" + "_contr_" + number;
                newRow.setAttribute("id", rowID);
                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + "Type of contract is " + selectedName + '</td>' +
                    ' <td>' + selectedName + '</td>' +
                    ' <td>' + selectedScore + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';

                $("#contr_tableOfRules tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });


    $("#period_add_row").click(function() {
        document.getElementById("period_tableOfRules").style.display = "table";
        var selectedName = $('#periodSelector').val();
        var selectedHelper = $('#period_SelectorHelper').val();
        var selectedScore = $('period_score').val();



        $.ajax({
            type: "POST",
            url: "/eJobFinder/rule/periodOfNotice",
            data: jQuery.param({
                name: selectedName,
                operator: selectedHelper,
                score: selectedScore
            }),
            timeout: 600000,
            success: function(data) {
                var number = splitter(data);
                var newRow = document.createElement('tr');
                var rowID = "row" + "_period_" + number;
                newRow.setAttribute("id", rowID);
                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + "Period of notice is " + selectedName + '</td>' +
                    ' <td>' + selectedName + '</td>' +
                    ' <td>' + selectedScore + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';
                $("#period_tableOfRules tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });

    $("#edu_add_row").click(function() {
        document.getElementById("edu_tableOfRules").style.display = "table";
        var selectedTitle = $('#edu_titleSelector').val();
        var selectedField = $('#edu_fieldSelector').val();
        var selectedMode = $('#edu_modeSelector').val();
        var selectedAbroad = $('#edu_abroadSelector').val();
        var selectedIsStudent = $('#edu_isStudentSelector').val();
        var selectedScore = $('#edu_score').val();


        $.ajax({
            type: "POST",
            url: "/eJobFinder/rule/education",
            data: jQuery.param({
                professionalTitle: selectedTitle,
                fieldOfStudy: selectedField,
                modeOfStudy: selectedMode,
                isAbroadStudent: selectedAbroad,
                isStudentParam: selectedIsStudent,
                score: selectedScore
            }),
            timeout: 600000,
            success: function(data) {

                var number = splitter(data);
                var newRow = document.createElement('tr');
                var rowID = "row" + "_edu_" + number;
                newRow.setAttribute("id", rowID);
                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + "Candidate is " + selectedTitle + '</td>' +
                    ' <td>' + selectedTitle + '</td>' +
                    ' <td>' + selectedField + '</td>' +
                    ' <td>' + selectedMode + '</td>' +
                    ' <td>' + selectedAbroad + '</td>' +
                    ' <td>' + selectedIsStudent + '</td>' +
                    ' <td>' + selectedScore + '</td>' +

                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';

                $("#edu_tableOfRules tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });

    $("#prev_add_row").click(function() {
        document.getElementById("prev_tableOfRules").style.display = "table";
        var selectedTitle = $('#prev_titleSelector').val();
        var selectedYears = $('#prev_yearSelector').val();
        var selectedYearsHelper = $('#prev_yearSelectorHelper').val();
        var selectedIsStillWorkingParam = $('#prev_stillWorking').val();
        var selectedHaveProfessionalExperienceParam = $('#prev_proExp').val();
        var selectedScore = $('#prev_score').val();

        $.ajax({
            type: "POST",
            url: "/eJobFinder/rule/previousEmployerRule",
            data: jQuery.param({
                name: selectedTitle,
                year: selectedYears,
                operator: selectedYearsHelper,
                isStillWorkingParam: selectedIsStillWorkingParam,
                haveProfessionalExperienceParam: selectedHaveProfessionalExperienceParam,
                score: selectedScore
            }),
            timeout: 600000,
            success: function(data) {
                var number = splitter(data);
                var newRow = document.createElement('tr');
                var rowID = "row" + "_prev_" + number;
                newRow.setAttribute("id", rowID);

                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + "Candidat was " + selectedTitle + " for " + selectedYearsHelper + " " + selectedYears + '</td>' +
                    ' <td>' + selectedTitle + '</td>' +
                    ' <td>' + selectedYears + '</td>' +
                    ' <td>' + selectedIsStillWorkingParam + '</td>' +
                    ' <td>' + selectedHaveProfessionalExperienceParam + '</td>' +

                    ' <td>' + selectedScore + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';
                $("#prev_tableOfRules tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });

    $("#salary_add_row").click(function() {
        document.getElementById("salary_tableOfRules").style.display = "table";
        var selectedAmountDown = $('#salary_amountDownSelector').val();
        var selectedAmountDownHelper = $('#salary_amountDownSelectorHelper').val();
        var selectedAmountUp = $('#salary_amountUpSelector').val();
        var selectedAmountUpHelper = $('#salary_amountUpSelectorHelper').val();
        var selectedScore = $('#salary_score').val();

        $.ajax({
            type: "POST",
            url: "/eJobFinder/rule/salary",
            data: jQuery.param({
                amountDown: selectedAmountDown,
                amountDownOperator: selectedAmountDownHelper,
                amountUp: selectedAmountUp,
                amountUpOperator: selectedAmountUpHelper,
                score: selectedScore
            }),
            timeout: 600000,
            success: function(data) {
                var number = splitter(data);
                var newRow = document.createElement('tr');
                var rowID = "row" + "_salary_" + number;
                newRow.setAttribute("id", rowID);
                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + "Minimum salary" + selectedAmountDownHelper + " " + selectedAmountDown + " and maxiumum salary" + selectedAmountUpHelper + " " + selectedAmountUp + '</td>' +
                    ' <td>' + selectedAmountDown + '</td>' +
                    ' <td>' + selectedAmountUp + '</td>' +

                    ' <td>' + selectedScore + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';
                $("#salary_tableOfRules tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });

    $("#sendFormButton").click(function() {

        $.ajax({
            type: "POST",
            url: "/eJobFinder/rule/finalize",
            data: jQuery.param({

            }),
            timeout: 600000,
            success: function(data) {

            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });
});

function deleteRow(rowName) {
    $.ajax({
        type: "POST",
        url: "/eJobFinder/rule/delete",
        data: jQuery.param({
            name: rowName
        }),
        timeout: 600000,
        success: function(data) {
            var selector = "#" + rowName;
            $(selector).remove();
        },
        error: function(e) {
            console.log('error while post');
        }
    });




}