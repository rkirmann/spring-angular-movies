import { Component, OnInit } from '@angular/core';
import { NavigationStart, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import {UIMessage} from "../../model/ui-message.model";
import {UiMessageService} from "../../service/ui-message.service";

@Component({
  selector: 'app-ui-messages',
  templateUrl: './ui-messages.component.html',
  styleUrls: ['./ui-messages.component.scss']
})
// Largely based on the example here: https://jasonwatmore.com/post/2020/07/16/angular-10-alert-notifications-example
export class UiMessagesComponent implements OnInit {

  messages: UIMessage[] = [];
  messageSubscription: Subscription | undefined;
  routeSubscription: Subscription | undefined;

  constructor(private router: Router,
    private uiMessageService: UiMessageService) { }

  ngOnInit(): void {
    this.messageSubscription = this.uiMessageService.onMessage().subscribe(message => {
      console.log("[UiMessagesComponent] Current message count: " + this.messages.length + ", adding a new one:", message);
      if (!message) {
        console.log("[UiMessagesComponent] Received message to keep only messages destined to stick around!")
        // Keep only the messages that were flagged to stay after a route change, and remove that flag so that upon
        // the next route change they get removed as normal
        this.messages = this.messages.filter(m => m.keepAfterRouteChange);
        this.messages.forEach(m => delete m.keepAfterRouteChange);
        console.log("[UiMessagesComponent] Messages after culling: ", this.messages)
        return;
      }

      this.messages.push(message);
    })

    this.routeSubscription = this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        console.log("[UiMessagesComponent] Caught nav event important enought to clean uiMessages", event);
        this.uiMessageService.clear();
      }
    });
  }

  ngOnDestroy() {
    if (this.messageSubscription)
      this.messageSubscription.unsubscribe();
    if (this.routeSubscription)
      this.routeSubscription.unsubscribe();
  }

  removeMessage(message: UIMessage) {
    console.log("[UiMessagesComponent] Removing message", message);
    if (!this.messages.includes(message)) {
      return;
    }

    this.messages.splice(this.messages.indexOf(message), 1);
  }
}
