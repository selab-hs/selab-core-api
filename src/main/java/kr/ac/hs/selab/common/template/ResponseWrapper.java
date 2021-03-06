package kr.ac.hs.selab.common.template;

import org.springframework.core.GenericTypeResolver;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ResponseWrapper implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(final MethodParameter returnType,
                            final Class<? extends HttpMessageConverter<?>> converterType) {

        if (Objects.isNull(returnType.getMethod())) {
            return false;
        }

        final var genericType = GenericTypeResolver.resolveReturnType(
                returnType.getMethod(),
                ResponseTemplate.class
        );

        return genericType.equals(ResponseTemplate.class);
    }

    @Override
    public Object beforeBodyWrite(final Object body, final MethodParameter returnType,
                                  final MediaType selectedContentType,
                                  final Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  final ServerHttpRequest request,
                                  final ServerHttpResponse response) {
        final var status = ((ResponseTemplate<?>) body).getStatus();

        response.setStatusCode(status);

        return body;
    }
}