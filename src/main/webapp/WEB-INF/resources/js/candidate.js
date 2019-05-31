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
        document.getElementById("tableOfFacts").style.display = "table";
        var selectedTechnology = $('#technologySelector').val();
        var selectedYears = $('#yearSelector').val();
        var selectedExperience = $('#experienceSelector').val();
        $(".selectpicker").val('default');
        $(".selectpicker").selectpicker("refresh");

        $.ajax({
            type: "POST",
            url: "/eJobFinder/fact/technology",
            data: jQuery.param({
                name: selectedTechnology,
                level: selectedExperience,
                year: selectedYears
            }),
            timeout: 600000,
            success: function(data) {
                var newRow = document.createElement('tr');
                var number = splitter(data);
                var rowID = "row" + "_tech_" + number;
                newRow.setAttribute("id", rowID);
                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + "Candidate should know " + selectedTechnology + " at level " + " " + selectedExperience + " with years of experience " + " " + selectedYears + '</td>' +
                    ' <td>' + selectedTechnology + '</td>' +
                    ' <td>' + selectedExperience + '</td>' +
                    ' <td>' + selectedYears + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';

                $("#tableOfFacts tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });

    $("#skill_add_row").click(function() {
        document.getElementById("skill_tableOfFacts").style.display = "table";
        var selectedName = $('#skillSelector').val();
        var selectedExperience = $('#skill_experienceSelector').val();
        var selectedScore = $('#skill_score').val();
        $(".selectpicker").val('default');
        $(".selectpicker").selectpicker("refresh");

        $.ajax({
            type: "POST",
            url: "/eJobFinder/fact/skill",
            data: jQuery.param({
                name: selectedName,
                level: selectedExperience
            }),
            timeout: 600000,
            success: function(data) {
                var number = splitter(data);
                var newRow = document.createElement('tr');
                var rowID = "row" + "_skill_" + number;
                newRow.setAttribute("id", rowID);
                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + "I have " + selectedName + " at level " + " " + selectedExperience + '</td>' +
                    ' <td>' + selectedName + '</td>' +
                    ' <td>' + selectedExperience + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';
                $("#skill_tableOfFacts tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });

    $("#tool_add_row").click(function() {
        document.getElementById("tool_tableOfFacts").style.display = "table";
        var selectedName = $('#tool_nameSelector').val();
        var selectedYears = $('#tool_yearSelector').val();
        var selectedExperience = $('#tool_experienceSelector').val();

        $(".selectpicker").val('default');
        $(".selectpicker").selectpicker("refresh");


        $.ajax({
            type: "POST",
            url: "/eJobFinder/fact/tool",
            data: jQuery.param({
                name: selectedName,
                level: selectedExperience,
                year: selectedYears
            }),
            timeout: 600000,
            success: function(data) {
                var number = splitter(data);
                var newRow = document.createElement('tr');
                var rowID = "row" + "_tool_" + number;
                newRow.setAttribute("id", rowID);
                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + "I know " + selectedName + " at level " + selectedExperience + " with years of experience "  + " " + selectedYears + '</td>' +
                    ' <td>' + selectedName + '</td>' +
                    ' <td>' + selectedExperience + '</td>' +
                    ' <td>' + selectedYears + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';
                $("#tool_tableOfFacts tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });


    $("#lang_add_row").click(function() {
        document.getElementById("lang_tableOfFacts").style.display = "table";
        var selectedName = $('#langSelector').val();
        var selectedExperience = $('#lang_experienceSelector').val();

        $(".selectpicker").val('default');
        $(".selectpicker").selectpicker("refresh");


        $.ajax({
            type: "POST",
            url: "/eJobFinder/fact/language",
            data: jQuery.param({
                name: selectedName,
                level: selectedExperience
            }),
            timeout: 600000,
            success: function(data) {
                var number = splitter(data);
                var newRow = document.createElement('tr');
                var rowID = "row" + "_lang_" + number;
                newRow.setAttribute("id", rowID);

                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + "I can speak " + selectedName + " at level " + " " + selectedExperience + '</td>' +
                    ' <td>' + selectedName + '</td>' +
                    ' <td>' + selectedExperience + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';

                $("#lang_tableOfFacts tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });

    $("#loc_add_row").click(function() {
        document.getElementById("loc_tableOfFacts").style.display = "table";
        var selectedName = $('#locSelector').val();

        $(".selectpicker").val('default');
        $(".selectpicker").selectpicker("refresh");

        $.ajax({
            type: "POST",
            url: "/eJobFinder/fact/location",
            data: jQuery.param({
                name: selectedName
            }),
            timeout: 600000,
            success: function(data) {
                var number = splitter(data);
                var newRow = document.createElement('tr');
                var rowID = "row" + "_loc_" + number;
                newRow.setAttribute("id", rowID);
                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + selectedName + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';


                $("#loc_tableOfFacts tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });

    $("#workH_add_row").click(function() {
        document.getElementById("workH_tableOfFacts").style.display = "table";
        var selectedName = $('#workHSelector').val();

        $(".selectpicker").val('default');
        $(".selectpicker").selectpicker("refresh");

        $.ajax({
            type: "POST",
            url: "/eJobFinder/fact/workingHours",
            data: jQuery.param({
                name: selectedName
            }),
            timeout: 600000,
            success: function(data) {
                var number = splitter(data);
                var newRow = document.createElement('tr');
                var rowID = "row" + "_workH_" + number;
                newRow.setAttribute("id", rowID);

                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + "I am ok with working hours like " + selectedName + '</td>' +
                    ' <td>' + selectedName + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';

                $("#workH_tableOfFacts tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });
    $("#contr_add_row").click(function() {
        document.getElementById("contr_tableOfFacts").style.display = "table";
        var selectedName = $('#contrSelector').val();

        $(".selectpicker").val('default');
        $(".selectpicker").selectpicker("refresh");


        $.ajax({
            type: "POST",
            url: "/eJobFinder/fact/typeOfContract",
            data: jQuery.param({
                name: selectedName
            }),
            timeout: 600000,
            success: function(data) {
                var number = splitter(data);
                var newRow = document.createElement('tr');
                var rowID = "row" + "_contr_" + number;
                newRow.setAttribute("id", rowID);
                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + "I am ok with type of contract  " + selectedName + '</td>' +
                    ' <td>' + selectedName + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';

                $("#contr_tableOfFacts tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });


    $("#period_add_row").click(function() {
        document.getElementById("period_tableOfFacts").style.display = "table";
        var selectedName = $('#periodSelector').val();

        $(".selectpicker").val('default');
        $(".selectpicker").selectpicker("refresh");
        $.ajax({
            type: "POST",
            url: "/eJobFinder/fact/periodOfNotice",
            data: jQuery.param({
                name: selectedName,
            }),
            timeout: 600000,
            success: function(data) {
                var number = splitter(data);
                var newRow = document.createElement('tr');
                var rowID = "row" + "_period_" + number;
                newRow.setAttribute("id", rowID);
                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + "My period of notice is " + selectedName + '</td>' +
                    ' <td>' + selectedName + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';

                $("#period_tableOfFacts tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });

    $("#edu_add_row").click(function() {
        document.getElementById("edu_tableOfFacts").style.display = "table";
        var selectedTitle = $('#edu_titleSelector').val();
        var selectedField = $('#edu_fieldSelector').val();
        var selectedMode = $('#edu_modeSelector').val();
        var selectedAbroad = $('#edu_abroadSelector').val();
        var selectedIsStudent = $('#edu_isStudentSelector').val();

        $(".selectpicker").val('default');
        $(".selectpicker").selectpicker("refresh");


        $.ajax({
            type: "POST",
            url: "/eJobFinder/fact/education",
            data: jQuery.param({
                professionalTitle: selectedTitle,
                fieldOfStudy: selectedField,
                modeOfStudy: selectedMode,
                isAbroadStudent: selectedAbroad,
                isStudentParam: selectedIsStudent
            }),
            timeout: 600000,
            success: function(data) {

                var number = splitter(data);
                var newRow = document.createElement('tr');
                var rowID = "row" + "_edu_" + number;
                newRow.setAttribute("id", rowID);
                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + selectedTitle + '</td>' +
                    ' <td>' + selectedField + '</td>' +
                    ' <td>' + selectedMode + '</td>' +
                    ' <td>' + selectedAbroad + '</td>' +
                    ' <td>' + selectedIsStudent + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';

                $("#edu_tableOfFacts tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });

    $("#prev_add_row").click(function() {
        document.getElementById("prev_tableOfFacts").style.display = "table";
        var selectedTitle = $('#prev_titleSelector').val();
        var selectedYears = $('#prev_yearSelector').val();
        var selectedYearsHelper = $('#prev_yearSelectorHelper').val();
        var selectedIsStillWorkingParam = $('#prev_stillWorking').val();
        var selectedHaveProfessionalExperienceParam = $('#prev_proExp').val();

        $(".selectpicker").val('default');
        $(".selectpicker").selectpicker("refresh");

        $.ajax({
            type: "POST",
            url: "/eJobFinder/fact/previousEmployerRule",
            data: jQuery.param({
                name: selectedTitle,
                year: selectedYears,
                operator: selectedYearsHelper,
                isStillWorkingParam: selectedIsStillWorkingParam,
                haveProfessionalExperienceParam: selectedHaveProfessionalExperienceParam
            }),
            timeout: 600000,
            success: function(data) {
                var number = splitter(data);
                var newRow = document.createElement('tr');
                var rowID = "row" + "_prev_" + number;
                newRow.setAttribute("id", rowID);

                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + "I was " + selectedTitle + " for " +" " + selectedYears + ' years</td>' +
                    ' <td>' + selectedTitle + '</td>' +
                    ' <td>' + selectedYears + '</td>' +
                    ' <td>' + selectedIsStillWorkingParam + '</td>' +
                    ' <td>' + selectedHaveProfessionalExperienceParam + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';
                $("#prev_tableOfFacts tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });

    $("#salary_add_row").click(function() {
        document.getElementById("salary_tableOfFacts").style.display = "table";
        var selectedAmountDown = $('#salary_amountDownSelector').val();
        var selectedAmountUp = $('#salary_amountUpSelector').val();

        $(".selectpicker").val('default');
        $(".selectpicker").selectpicker("refresh");

        $.ajax({
            type: "POST",
            url: "/eJobFinder/fact/salary",
            data: jQuery.param({
                amountDown: selectedAmountDown,
                amountUp: selectedAmountUp
            }),
            timeout: 600000,
            success: function(data) {
                var number = splitter(data);
                var newRow = document.createElement('tr');
                var rowID = "row" + "_salary_" + number;
                newRow.setAttribute("id", rowID);
                newRow.innerHTML = ' <td class="index">' + number + '</td>' +
                    ' <td>' + "I want to earn minimum " + selectedAmountDown + "</td>" +
                    ' <td>' + selectedAmountDown + '</td>' +
                    ' <td>' + selectedAmountUp + '</td>' +
                    ' <td>' + " <button type='button' class='btn btn-danger'  onclick=deleteRow('" + rowID + "')>" +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                    '</button>' +
                    '</td>';
                $("#salary_tableOfFacts tbody").append(newRow);
            },
            error: function(e) {
                console.log('error while post');
            }
        });


    });

    $("#sendFormButton").click(function() {

        $.ajax({
            type: "POST",
            url: "/eJobFinder/fact/finalize",
            data: jQuery.param({

            }),
            timeout: 600000,
            success: function(data) {
                var link = "http://localhost:8080/eJobFinder/jobOfferList";
                $(location).attr('href', link);
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
        url: "/eJobFinder/fact/delete",
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