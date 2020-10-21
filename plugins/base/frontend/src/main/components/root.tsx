import React from 'react';
import { render } from 'react-dom';
import _ from "lodash";

import App from "./app";
import './app/index.scss';
import { NavigationPaneSearch } from './navigationPaneSearch/navigationPaneSearch';
import { PageSummary } from './pageSummary/pageSummary';

const renderNavigationPane = () => {
  render(
    <NavigationPaneSearch />,
    document.getElementById('paneSearch')
  )
}

const renderOnThisPage = () => {
  for (const e of document.querySelectorAll('.tabs-section-body > div[data-togglable]')) {
    const entries = Array.from(e.querySelectorAll('a[anchor-label]')).map((element: HTMLElement) => {
      return {
        location: element.getAttribute('data-name'),
        label: element.getAttribute('anchor-label'),
        sourceSets: _.sortBy(element.getAttribute('data-filterable-set').split(' '))
      }
    })
    const unique = _.uniqBy(entries, ({label}) => label)
    if (unique.length) {
      const element = document.createElement('div')
      render(<PageSummary entries={unique} />, element)
      e.appendChild(element)
    }
  }
}

let renderApp = () => {
  console.log('render')
  const appEl = document.getElementById('searchBar');
  const rootEl = document.createElement('div');
  render(
    <App />,
    rootEl
  );
  appEl.appendChild(rootEl);
  renderNavigationPane();
  renderOnThisPage();

  document.removeEventListener('DOMContentLoaded', renderApp);
};

document.addEventListener('DOMContentLoaded', renderApp);