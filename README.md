# Telestion Application Template

This is a template for Telestion applications.
It helps you to set up the publishing of Telestion applications.
Dependencies to the [main repo](https://github.com/TelestionTeam/telestion) are included, too.

## Necessary Changes

* [ ] `settings.gradle` - set `rootProject.name`
* [ ] `Dockerfile` - replace `TelestionTemplate` in the CMD block by the new project name 
* [ ] `build.gradle` - replace `group` by your group name
* [ ] `.github/workflows/publishImage.yml` - update `ORGANIZATION_OR_USER_NAME/REPO_NAME/IMAGE_NAME`
* [ ] `conf/config.json` - adapt to fit your needs
* [ ] `src/main/java` - add your source code
* [ ] Update readme

## Example Project

A working example is provided in the [RocketSound repository](https://github.com/TelestionTeam/telestion-rocketsound).
