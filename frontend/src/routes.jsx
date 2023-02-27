import React from "react";
import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  Navigate,
} from "react-router-dom";
import {
  Feed,
  Login,
  Register,
  CampaingPage,
  InterestPage,
  Profile,
  SearchUser,
  ErrorPage,
  AnimalPage,
} from "@/pages";
import { ROUTES } from "@/constants";
import { useGlobalUser } from "./context/user.context";
import { getUserIsVoluntary } from "./utils";

const ProtectedRoute = ({ element }) => {
  const [user] = useGlobalUser();

  if (!user) {
    return <Navigate to={ROUTES.login} replace exact />;
  }

  return element;
};

const VoluntaryRoute = ({ element, isInterestPath = false }) => {
  const [user] = useGlobalUser();

  if (!user) {
    return <Navigate to={ROUTES.login} replace exact />;
  }

  const isFirstAcessString = localStorage.getItem("isFirstAcess") || null;
  const isFirstAcess = JSON.parse(isFirstAcessString);

  if (isFirstAcess && !isInterestPath) {
    return <Navigate to={ROUTES.interest} replace exact />;
  }

  const isVoluntary = getUserIsVoluntary();

  if (isVoluntary) {
    return element;
  } else {
    return <Navigate to={ROUTES.profile} replace exact />;
  }
};

const OngRoute = ({ element }) => {
  const [user] = useGlobalUser();
  if (!user) {
    return <Navigate to={ROUTES.login} replace exact />;
  }

  const isVoluntary = getUserIsVoluntary();

  if (isVoluntary) {
    return <Navigate to={ROUTES.feed} replace exact />;
  }

  return element;
};

const PublicRoute = ({ element }) => {
  const [user] = useGlobalUser();

  if (user) {
    return <Navigate to={ROUTES.feed} replace exact />;
  }

  return element;
};
export const router = createBrowserRouter(
  createRoutesFromElements(
    <>
      <Route
        path={ROUTES.feed}
        element={<VoluntaryRoute element={<Feed />} />}
        exact
        errorElement={<ErrorPage />}
      />

      <Route
        path={ROUTES.profile}
        element={<ProtectedRoute element={<Profile />} />}
        exact
        errorElement={<ErrorPage />}
      />
      <Route
        path={ROUTES.animal}
        element={<OngRoute element={<AnimalPage />} />}
        exact
        errorElement={<ErrorPage />}
      />

      <Route
        path={ROUTES.login}
        element={<PublicRoute element={<Login />} />}
        exact
        errorElement={<ErrorPage />}
      />

      <Route
        path={ROUTES.campaing}
        element={<OngRoute element={<CampaingPage />} />}
        exact
        errorElement={<ErrorPage />}
      />

      <Route
        path={ROUTES.interest}
        element={
          <VoluntaryRoute isInterestPath={true} element={<InterestPage />} />
        }
        exact
        errorElement={<ErrorPage />}
      />

      <Route
        path={ROUTES.register}
        element={<PublicRoute element={<Register />} />}
        errorElement={<ErrorPage />}
      />

      <Route
        path={ROUTES.search}
        element={<ProtectedRoute element={<SearchUser />} />}
        exact
        errorElement={<ErrorPage />}
      />

      <Route path="*" element={<p>There's nothing here: 404!</p>} />
    </>
  )
);
