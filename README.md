# AndroidViewEventDemo
View事件体系 -- 滑动冲突


1.外部拦截法（伪代码） ---点击事件都先经过父容器的拦截处理
          
          public boolean onInterceptTouchEvent(MotionEvent motionEvent){
                  boolean intercept = false;
                  int x = (int)event.getX();
                  int y = (int)event.getY();
                  switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                              intercepted = false;
                              break;
                        case MotionEvent.ACTION_MOVE:
                              if(父容器需要当前点击事件){
                                intercepted = true;
                              }else{
                                intercepted = false;
                              }
                              break;
                              
                        case MotionEvent.ACTION_UP:
                                intercepted = false;
                              break;
                        default:
                              break;
                  }
                  
                  mLastXIntercept = x;
                  mLastYIntercept = y;
                  return intercepted;
          }
          
          
  2.内部拦截法（伪代码）      --- 父容器不拦截任何事件，所有的事件都传递给子元素，如果子元素需要此事件就直接消耗掉，否则交由父容器进行处理。
  
        在子容器中:
              public boolean dispatchTouchEvent(MotionEvent event){
                   int x = (int)event.getX();
                   int y = (int)event.getY();
                   
                   switch(event.getAction()){
                      
                      case MotionEvent.ACTION_DOWN:
                            parent.requestDisallowInterceptTouchEvent(true);
                            break;
                      case MotionEvent.ACTION_MOVE:
                           int deltaX = x - mLastX;
                           int deltaY = y - mLastY;
                           
                           if(父容器需要此类点击事件){
                              parent.requestDisallowInterceptTouchEvent(false);
                           }
                           
                           break;
                           
                           
                       case MotionEvent.ACTION_UP:
                            break;
                            
                       default: 
                            break;
                   }
                   
                   mLastX = x;
                   mLastY = y;
                   
                   return super.dispatchTouchEvent(event);
              }
              
       在父容器中:
              public boolean onInterceptTouchEvent(MotionEvent event){
                  int action = evnet.getAction();
                  if(action == MotionEvent.ACTION_DOWN){
                        return false;
                  }else{
                        return true;
                  }
              }
          
