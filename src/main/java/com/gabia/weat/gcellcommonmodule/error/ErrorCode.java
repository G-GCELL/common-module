package com.gabia.weat.gcellcommonmodule.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, CustomStatus.UNKNOWN_ERROR, "알 수 없는 오류가 발생하였습니다."),
	MINIO_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, CustomStatus.MINIO_ERROR, "MinIO 라이브러리 에러"),
	EXCEL_NOT_EXISTS(HttpStatus.NOT_FOUND, CustomStatus.EXCEL_NOT_EXISTS, "해당 엑셀파일이 존재하지 않습니다."),
	EXCEL_NOT_MATCHES(HttpStatus.FORBIDDEN, CustomStatus.EXCEL_NOT_MATCHES, "액셀파일 유저와 해당 유저가 일치하지 않습니다."),
	EXCEL_DELETED(HttpStatus.BAD_REQUEST, CustomStatus.EXCEL_DELETED, "삭제된 엑셀파일입니다."),
	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, CustomStatus.MEMBER_NOT_FOUND, "해당 사용자가 존재하지 않습니다."),
	DUPLICATE_FILE_NAME(HttpStatus.CONFLICT, CustomStatus.DUPLICATE_FILE_NAME, "해당 파일 이름이 이미 존재합니다."),
	CONNECTION_NOT_FOUND(HttpStatus.NOT_FOUND, CustomStatus.CONNECTION_NOT_FOUND, "해당 사용자의 연결을 찾을 수 없습니다."),
	CONNECTION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, CustomStatus.CONNECTION_ERROR, "사용자 연결에 문제가 발생하였습니다."),
	INVALID_FILE_EXTENSION(HttpStatus.BAD_REQUEST, CustomStatus.INVALID_FILE_EXTENSION, "파일 확장자가 올바르지 않습니다."),
	MESSAGE_BROKER_CONNECTION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, CustomStatus.MESSAGE_BROKER_CONNECTION_ERROR, "메시지 브로커 연결에 문제가 발생하였습니다."),
	URI_SYNTAX_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, CustomStatus.URI_SYNTAX_ERROR, "Hiworks Authorization URI가 올바르지 않습니다."),
	HIWORKS_ACCESS_TOKEN_REQUEST_ERROR(HttpStatus.BAD_REQUEST, CustomStatus.HIWORKS_ACCESS_TOKEN_REQUEST_ERROR, "Hiworks API 액세스 토큰 요청이 올바르지 않습니다."),
	UNAUTHORIZED_REQUEST(HttpStatus.UNAUTHORIZED, CustomStatus.UNAUTHORIZED_REQUEST, "해당 리소스에 접근하기 위해 인증이 필요합니다."),
	ACCESS_FORBIDDEN(HttpStatus.FORBIDDEN, CustomStatus.ACCESS_FORBIDDEN, "해당 리소스에 접근할 수 있는 권한이 없습니다."),
	INVALID_TOKEN(HttpStatus.BAD_REQUEST, CustomStatus.INVALID_TOKEN, "유효하지 않은 액세스 토큰입니다."),
	ESSENTIAL_VALUE_ERROR(null, CustomStatus.ESSENTIAL_VALUE_ERROR, "올바르지 않은 파라미터 형식입니다."),
	NO_RESULT(null,CustomStatus.NO_RESULT, "조건에 해당하는 결과가 없습니다."),
	EXCEL_WRITE_FAIL(null,CustomStatus.EXCEL_WRITE_FAIL, "엑셀 파일 작성에 실패하였습니다."),
	INVALID_COLUMN_NAME(null,CustomStatus.INVALID_COLUMN_NAME, "유효하지 않은 컬럼 이름입니다."),
	MINIO_UPLOAD_FAIL(null,CustomStatus.MINIO_UPLOAD_FAIL , "파일 업로드에 실패하였습니다."),
	MINIO_DOWNLOAD_FAIL(null,CustomStatus.MINIO_DOWNLOAD_FAIL , "파일 다운로드에 실패하였습니다."),
	INVALID_CSV_HEADER(null,CustomStatus.INVALID_CSV_HEADER,"CSV 파일 헤더가 올바르지 않습니다."),
	INVALID_CSV_FORMAT(null,CustomStatus.INVALID_CSV_FORMAT, "CSV 파일 형식이 올바르지 않습니다."),
	MAIL_SEND_FAIL(null,CustomStatus.MAIL_SEND_FAIL, "메일 전송에 실패하였습니다."),
	RESULT_COUNT_EXCEED(null,CustomStatus.RESULT_COUNT_EXCEED, "요청 결과가 150만 건을 초과하였습니다.");

	private final HttpStatus status;
	private final CustomStatus customStatus;
	private final String message;

}