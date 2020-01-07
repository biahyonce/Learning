<template>
    <div id="burger"
         :class="{ 'active' : isBurgerActive }"
         @click.prevent="toggle">
        <slot>
            <button type="button" class="burger-button" title="Menu">
                <span class="burger-bar burger-bar--1"></span>
                <span class="burger-bar burger-bar--2"></span>
                <span class="burger-bar burger-bar--3"></span>
            </button>
        </slot>
    </div>
</template>

<script>
import {store, mutations} from "../../store";

export default {
    computed: {
        isBurgerActive() {
            return store.isNavOpen
        }
    },
    methods: {
        toggle: mutations.toggleNav
    }
}
</script>

<style>
    html, body { height: 100%; }

    body {
        display: grid;
        margin: 0;
    }

    #app {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .hidden {
        visibility: hidden;
    }

    /** burger **/


    button {
        cursor: pointer;
    }

    /* remove blue outline */
    button:focus {
        outline: 0;
    }

    .burger-button {
        position: fixed;
        height: 10%;
        width: 3%;
        float: left;
        z-index: 999;
        left: 0;
        top: 0;
        background-color: transparent;
        border: 0;
        border-radius: 0;
        display: block;
        pointer-events: all;
        transition: transform .6s cubic-bezier(.165,.84,.44,1);
    }

    .burger-bar {
        background-color: #000;
        position: absolute;
        top: 50%;
        right: 6px;
        left: 6px;
        height: 2px;
        width: auto;
        margin-top: -1px;
        transition: transform .6s cubic-bezier(.165,.84,.44,1),opacity .3s cubic-bezier(.165,.84,.44,1),background-color .6s cubic-bezier(.165,.84,.44,1);
    }

    .burger-bar--1 {
        -webkit-transform: translateY(-6px);
        transform: translateY(-6px);
    }

    .burger-bar--2 {
        transform-origin: 100% 50%;
        transform: scaleX(.8);
    }

    .burger-bar--3 {
        transform: translateY(6px);
    }

    .burger-button:hover .burger-bar--2 {
        /* Make the second bar moves when mouse over */
        transform: scaleX(1);
    }

    .no-touchevents .burger-bar--2:hover {
        transform: scaleX(1);
    }

    #burger.active .burger-button {
        transform: rotate(-180deg);
    }

    #burger.active .burger-bar--1 {
        transform: rotate(45deg)
    }

    #burger.active .burger-bar--2 {
        opacity: 0;
    }

    #burger.active .burger-bar--3 {
        transform: rotate(-45deg)
    }
</style>