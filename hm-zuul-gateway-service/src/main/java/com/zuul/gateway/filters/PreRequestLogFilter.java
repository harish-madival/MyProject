package com.zuul.gateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PreRequestLogFilter extends ZuulFilter {
  private static final Logger log = LoggerFactory.getLogger(PreRequestLogFilter.class);

  @Override public String filterType() { return "pre"; }
  @Override public int filterOrder() { return 1; }
  @Override public boolean shouldFilter() { return true; }

  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest req = ctx.getRequest();
    log.info("{} {} from {} UA:{}", req.getMethod(), req.getRequestURI(), req.getRemoteAddr(), req.getHeader("User-Agent"));
    return null;
  }
}
