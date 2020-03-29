package noah.memo.framework.aop;

import noah.memo.framework.bean.response.RspData;
import noah.memo.framework.bean.response.RspStatus;
import noah.memo.framework.exception.BusinessException;
import noah.memo.framework.log.Logger;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionTranslator {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public RspData handleError(MissingServletRequestParameterException e) {
        Logger.error("Missing Request Parameter", e);
        String message = String.format("Missing Request Parameter: %s", e.getParameterName());
        return RspData
                .builder()
                .status(RspStatus.PARAM_MISS.status)
                .msg(message)
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public RspData handleError(MethodArgumentTypeMismatchException e) {
        Logger.error("Method Argument Type Mismatch", e);
        String message = String.format("Method Argument Type Mismatch: %s", e.getName());
        return RspData
                .builder()
                .status(RspStatus.PARAM_TYPE_ERROR.status)
                .msg(message)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RspData handleError(MethodArgumentNotValidException e) {
        Logger.error("Method Argument Not Valid", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return RspData
                .builder()
                .status(RspStatus.PARAM_VALID_ERROR.status)
                .msg(message)
                .build();
    }

    @ExceptionHandler(BindException.class)
    public RspData handleError(BindException e) {
        Logger.error("Bind Exception", e);
        FieldError error = e.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return RspData
                .builder()
                .status(RspStatus.PARAM_BIND_ERROR.status)
                .msg(message)
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public RspData handleError(ConstraintViolationException e) {
        Logger.error("Constraint Violation", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
        String message = String.format("%s:%s", path, violation.getMessage());
        return RspData
                .builder()
                .status(RspStatus.PARAM_VALID_ERROR.status)
                .msg(message)
                .build();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public RspData handleError(NoHandlerFoundException e) {
        Logger.error("404 Not Found", e);
        return RspData
                .builder()
                .status(RspStatus.NOT_FOUND.status)
                .msg(e.getMessage())
                .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public RspData handleError(HttpMessageNotReadableException e) {
        Logger.error("Message Not Readable", e);
        return RspData
                .builder()
                .status(RspStatus.MSG_NOT_READABLE.status)
                .msg(e.getMessage())
                .build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public RspData handleError(HttpRequestMethodNotSupportedException e) {
        Logger.error("Request Method Not Supported", e);
        return RspData
                .builder()
                .status(RspStatus.METHOD_NOT_SUPPORTED.status)
                .msg(e.getMessage())
                .build();
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public RspData handleError(HttpMediaTypeNotSupportedException e) {
        Logger.error("Media Type Not Supported", e);
        return RspData
                .builder()
                .status(RspStatus.MEDIA_TYPE_NOT_SUPPORTED.status)
                .msg(e.getMessage())
                .build();
    }

    @ExceptionHandler(BusinessException.class)
    public RspData handleError(BusinessException e) {
        Logger.error("business Exception", e);
        return RspData
                .builder()
                .status(e.code)
                .msg(e.getMessage())
                .build();
    }

    @ExceptionHandler(Throwable.class)
    public RspData handleError(Throwable e) {
        Logger.error("Internal Server Error", e);
        return RspData
                .builder()
                .status(RspStatus.INTERNAL_SERVER_ERROR.status)
                .msg(e.getMessage())
                .build();
    }
}
