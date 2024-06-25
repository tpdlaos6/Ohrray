//댓글 가져오기
function getListQNA(){
    var pno = document.querySelector(".pno").value;
    console.log(pno);
    $.ajax({
            url: "/reply/shopping/getQNAList", // 요청 URL
            type: "get", // HTTP 메서드(전송방식)
            data: { // 매개변수로 전달할 데이터
                pno : pno // 부모글번호
            },
            dataType: "json", // 응답 데이터 형식
            success: sucFuncJson1, // 요청 성공 시 호출할 메서드 설정
            error: errFunc1 // 요청 실패 시 호출할 메서드 설정
        });
}

function sucFuncJson1(result) {
    console.log(result);
    var str ="";
    result.forEach(function(list, index) {
        console.log(list.content);
        console.log(index);
            str +="<li class='left clearfix' data-rno='"+list.id+"'>";
            str +="  <div><div class='header'><strong class='primary-font'>["
                +list.id+"] "+list.writer+"</strong>";
            str +="    <small class='pull-right text-muted'>"
                +list.regDate+"</small></div>";
            str +="    <p>"+list.content+"</p></div>"
            str +=" <button class='deleteReply float-right' type='button'>댓글삭제</button>  "
             str +=  "<button class='updateReply float-right' type='button'>댓글수정</button></li><hr/>";
    });
    $(".chatQnA").html(str);
}
function errFunc1(e) {
    console.log("실패");
}

//댓글 추가하기
document.querySelector(".addQNA").addEventListener("click", function (e) {
    e.preventDefault();
    var pno = document.querySelector(".pno").value;

    var content =  document.querySelector(".qnacontent").value;
    $.ajax({
        url: "/reply/shopping/addQNA", // 요청 URL
        type: "post", // HTTP 메서드(전송방식)
        data: { // 매개변수로 전달할 데이터
            pno : pno,
            content : content, // 검색어
            writer : "me"
        },
        dataType: "json", // 응답 데이터 형식
        success: sucFuncJson, // 요청 성공 시 호출할 메서드 설정
        error: errFunc // 요청 실패 시 호출할 메서드 설정
    });
});

// 검색 성공 시 결과를 화면에 뿌려줍니다.
function sucFuncJson(result) {
    console.log("성공");
    document.querySelector(".qnacontent").value="";
    getList();
}
function errFunc(e) {
    console.log("실패");
}


//댓글 수정하기
document.querySelector(".updateQNA").addEventListener("click", function (e) {
    e.preventDefault();
    var id = document.querySelector(".qnaId").value;
    var question = document.querySelector(".qnacontent").value;
    $.ajax({
        url: "/reply/shopping/updateQNA", // 요청 URL
        type: "put", // HTTP 메서드(전송방식)
        data: { // 매개변수로 전달할 데이터
            id: id,
            question: question, // 검색어
            answer: "답변",
            rating: 1.0,
            mid: 1
        },
        dataType: "json", // 응답 데이터 형식
        success: sucFuncJson2, // 요청 성공 시 호출할 메서드 설정
        error: errFunc2 // 요청 실패 시 호출할 메서드 설정
    });
});



// 검색 성공 시 결과를 화면에 뿌려줍니다.
function sucFuncJson2(result) {
    console.log("성공2");
}
function errFunc2(e) {
    console.log("실패2");
}

//댓글 삭제하기
document.querySelector(".deleteQNA").addEventListener("click", function (e) {
    e.preventDefault();
    var id = 1;
    $.ajax({
        url: "/reply/shopping/deleteQNA", // 요청 URL
        type: "delete", // HTTP 메서드(전송방식)
        data: { // 매개변수로 전달할 데이터
            id : id
        },
        dataType: "json", // 응답 데이터 형식
        success: sucFuncJson3, // 요청 성공 시 호출할 메서드 설정
        error: errFunc3 // 요청 실패 시 호출할 메서드 설정
    });
});

// 검색 성공 시 결과를 화면에 뿌려줍니다.
function sucFuncJson3(result) {
    console.log("성공3");
}
function errFunc3(e) {
    console.log("실패3");
}
