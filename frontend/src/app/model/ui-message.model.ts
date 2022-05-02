export class UIMessage {
  constructor(public type: UIMessageType, public text: String, public keepAfterRouteChange?: boolean) { };
}

export enum UIMessageType {
  INFO = "alert-primary",
  SUCCESS = "alert-success",
  ERROR = "alert-danger"
}