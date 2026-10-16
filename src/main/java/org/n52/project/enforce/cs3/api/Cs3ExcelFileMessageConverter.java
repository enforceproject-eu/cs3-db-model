package org.n52.project.enforce.cs3.api;

import java.io.InputStream;

import org.springframework.http.converter.AbstractHttpMessageConverter;

//public class Cs3ExcelFileMessageConverter extends AbstractHttpMessageConverter<InputStream> {
//
//    public static final MediaType CS3_DATA =
//            new MediaType("application", "enforce-cs3-themis-data");
//
//    @Override
//    protected boolean supports(Class<?> clazz) {
//        return InputStream.class.isAssignableFrom(clazz);
//    }
//
//    @Override
//    protected InputStream readInternal(Class<? extends InputStream> clazz,
//            HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
//        return inputMessage.getBody();
//    }
//
//    @Override
//    protected void writeInternal(InputStream t,
//            HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
//        IOUtils.copyLarge(t, outputMessage.getBody());
//    }
//
//    @Override
//    protected MediaType getDefaultContentType(InputStream t) throws IOException {
//        return CS3_DATA;
//    }    
//    
//
//    @Override
//    public List<MediaType> getSupportedMediaTypes() {
//        return Collections.singletonList(CS3_DATA);
//    }
//
//    @Override
//    public Charset getDefaultCharset() {
//        return Charset.forName("UTF-8");
//    }
//
//}
