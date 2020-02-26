import { mount } from "@vue/test-utils";
import AppHeader from "../../src/components/AppHeader";

// Difference between mount and shallowMount
// Shallow: return a simple implementation of a component that has lots of children
// Mount: return the fully component

describe("AppHeader", () => {
   test('if logged in is false do not show logout button', () => {
       const wrapper = mount(AppHeader);
       expect(wrapper.find("button").isVisible()).toBe(false);
   })

    test("if logged in show logout button", () => {
        const wrapper = mount(AppHeader);
        wrapper.setData({loggedIn: true});
        expect(wrapper.find("button").isVisible()).toBe(true);
    })
});