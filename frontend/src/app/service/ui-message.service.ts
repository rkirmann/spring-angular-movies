import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { UIMessage, UIMessageType } from '../model/ui-message.model';

@Injectable({
  providedIn: 'root'
})
export class UiMessageService {

  private subject = new Subject<UIMessage>();

  constructor() { }

  onMessage(): Observable<UIMessage> {
    return this.subject.asObservable();
  }

  success(text: string, keepAfterRouteChange?:boolean){
    this.createMessage(new UIMessage(UIMessageType.SUCCESS, text, keepAfterRouteChange));
  }

  info(text: string, keepAfterRouteChange?:boolean){
    this.createMessage(new UIMessage(UIMessageType.INFO, text, keepAfterRouteChange));
  }

  error(text: string, keepAfterRouteChange?:boolean){
    this.createMessage(new UIMessage(UIMessageType.ERROR, text, keepAfterRouteChange));
  }

  createMessage(message: UIMessage){
    this.subject.next(message);
  }

  clear() {
    // @ts-ignore
    this.subject.next(null);
  }
}
