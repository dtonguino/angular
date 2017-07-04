import { AngularProjectManagerPage } from './app.po';

describe('angular-project-manager App', () => {
  let page: AngularProjectManagerPage;

  beforeEach(() => {
    page = new AngularProjectManagerPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
