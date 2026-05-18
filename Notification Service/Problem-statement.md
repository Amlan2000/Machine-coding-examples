Comms Service — Low-Level Design
Problem
Build a service that sends notifications to customers across multiple channels (SMS, Email, WhatsApp) by integrating with multiple third-party providers. Each provider supports one or more channels, and each channel can be served by multiple providers. This application accepts a send request from a client, picks an appropriate provider, resolves the payload, and dispatches the notification.
Core Requirements
1. Multi-channel, multi-provider
   Each provider supports one or more channels. Example: Gupshup can serve both WhatsApp and SMS.
   Each channel can be served by multiple providers. Example: SMS can go through Gupshup, Twilio, etc.
   A send request specifies the channel. The service must route to a provider that supports that channel.
2. Provider availability and selection
   Each provider exposes an availability check API (up / down / accepting requests).
   Among eligible providers (available + supports the requested channel) pick one. A simple random pick is acceptable but the selection mechanism must be extensible.
3. Templating and payload resolution
   The client never sends raw message content. Instead, the client sends a templateName, a set of templateParameters, and the target channel.
   Example request:
   {
   "recipientInfo": "<necessary info>",
   "templateName": "LOGIN_OTP",
   "templateParameters": {
   "otp": 12345
   },
   "channel": "SMS",
   "messageType": "TRANSACTIONAL|PROMOTIONAL|OTP"
   }

A template is a stored, channel-aware blueprint of a message:
A single templateName (e.g. LOGIN_OTP) holds different content for different channels — SMS has a text body, Email has subject + body, WhatsApp has its own template reference + parameters.
Each piece of content has placeholders (e.g. Your OTP is {otp}) that get filled in at send time using templateParameters.
At dispatch time the service must:
Look up the template by templateName
Substitute placeholders using templateParameters to produce a fully resolved, channel-shaped message.
Pick the channel-specific content for the requested channel.
The system should make it easy to add a new template, support a new channel for an existing template, or onboard a new provider with minimal changes to existing code.
4. Provider credential configuration
   For simplicity consider basic authentication through username + password, but needs to be extensible.
5. Webhook handling
   Assume delivery status arrives from providers via HTTP webhooks.
   Handle the authenticity of webhooks, if it is coming from the trusted source.
   Adding a new provider's webhook should not require changes to existing providers' webhook handling.
6. Demo driver
   A driver class that exercises the system end-to-end:
   Configure couple of templates for different channels
   Configures two or more providers with necessary credentials and webhooks.
   Send a notification on each channel.
   Simulates a provider being unavailable and another being chosen.
   Notes
   No database, no external store. Use in-memory data structures only.
   No UI.
   No unit tests required. A driver class demonstrating the flows is sufficient.
   Prioritise code execution and completion.
   Use proper exception handling with typed exceptions at boundaries.
   Validate wherever needed.
   Expectations
   Clear abstraction boundaries between channel, provider, template, credential, and dispatching.
   Open-closed adherence — adding a new channel, provider, template, auth scheme, or selection strategy should be a small, localized diff.
   Typed exceptions, handled at the right layer.
   Code that is modular, readable, and testable.
   Followups
   Dynamic vendor switching
   At runtime, without restarting the service, it should be possible to:
   Disable a provider that is misbehaving.
   Change the provider selection strategy (e.g. switch from random to weighted, or to health-driven).
   Re-enable a provider once recovered.
   Retries
   Whenever a provider fails to deliver the dispatched request, a re-delivery attempt is made with an appropriate provider.
   This should be configurable at the template level.
   Ex: For the OTP template, one might want to retry with a different SMS provider or through a WhatsApp provider.



