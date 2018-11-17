package clients;

class MockTextMessageClient extends TextMessageClient {

    @Override
    EmailClient emailClient() {
        return new MockEmailClient();
    }
}
